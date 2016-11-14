package com.ippon.jug.poule.Controller;

import com.ippon.jug.poule.Counter.QueryCounter;
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
}
