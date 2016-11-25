package com.ippon.jug.poule.Controller;

import com.ippon.jug.poule.Counter.QueryCounter;
import com.ippon.jug.poule.Schedduler.CotcotSchedduler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class QPSController {

    private QueryCounter queryCounter;

    public QPSController(QueryCounter queryCounter, CotcotSchedduler cotcotSchedduler) {
        this.queryCounter = queryCounter;
    }

    @RequestMapping(value = "/qps")
    public Map<String, Integer> getMeanQps() {
        return queryCounter.getQps();
    }

    @RequestMapping(value = "/qpsSinceStarted")
    public Float getMeanQpsSinceStarted() {
        return queryCounter.getQpsSinceStarted();
    }

    @RequestMapping(value = "/lastSecQps")
    public Integer getLastSecQps() {
        return queryCounter.getLastSecCount();
    }

    @RequestMapping(value = "/getHosts", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> getHostNames() {
        return queryCounter.getHostMap().keySet();
    }
}
