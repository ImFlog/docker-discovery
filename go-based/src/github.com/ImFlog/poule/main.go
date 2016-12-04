package main

import (
	"fmt"
	"net/http"
	"log"
	"io/ioutil"
	"time"
)

// TODO: create packages !
func main() {
	go startWorkers()                          // Start workers
	startWebServer()                          // Starts HTTP service
}

func startWebServer() {
	log.Println("Starting HTTP service at 8080")

	// Endpoint which returns html graph
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		body, _ := ioutil.ReadFile("html/index.html")
		fmt.Fprint(w, string(body))
	})

	// Endpoint which return hostNames
	http.HandleFunc("/getHosts", func(w http.ResponseWriter, r *http.Request) {
		res := ""
		for k := range hosts {
			res += k + "\n"
		}
		fmt.Fprintf(w, "%s", res)
	})

	// Endpoint which return previous qps stats
	http.HandleFunc("/lastSecQps", func(w http.ResponseWriter, r *http.Request) {
		sec := qps[time.Now().Second() - 1]
		if sec < 0 {
			sec = 60
		}
		fmt.Fprintf(w, "%d", sec)
	})

	log.Fatal(http.ListenAndServe(":8080", nil))
}

// host list
var hosts = make(map[string]bool)

func cotCotWorker() {
	res, err := http.Get("http://slip:8080/request")
	if err != nil {
		time.Sleep(100 * time.Millisecond)
		log.Println(err)
		return
	}
	// Read return value
	byteMessage, err := ioutil.ReadAll(res.Body)
	res.Body.Close()
	if err != nil {
		log.Println(err)
		return
	}
	hostString := string(byteMessage)
	// Add host to result if doesn't exist
	if !hosts[hostString] {
		hosts[hostString] = true
	}
}

// 10 workers
func startWorkers() {
	maxGoroutines := 10
	guard := make(chan struct{}, maxGoroutines)

	// Infinite filling
	for {
		guard <- struct{}{} // would block if guard channel is already filled
		go func() {
			cotCotWorker()
			addQuery()
			<-guard
		}()
	}
}

// query counter
var qps = make(map[int]int)

func addQuery() {
	t := time.Now()
	if t.Second() == 0 {
		qps = make(map[int]int)
	}

	if _, ok := qps[t.Second()]; ok {
		qps[t.Second()] += 1
	} else {
		qps[t.Second()] = 1
	}
}