package com.ippon.jug.slip.Controller;

import com.ippon.jug.slip.Counter.QueryCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class QPSController {

    private QueryCounter queryCounter;

    @Autowired
    public QPSController(QueryCounter queryCounter){
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
}
