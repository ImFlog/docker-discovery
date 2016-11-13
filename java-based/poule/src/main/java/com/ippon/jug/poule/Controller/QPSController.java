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

        // float elapsedTime = (System.currentTimeMillis() - startTime) / 1000F;
        // Map<String, Object> result = new HashMap<>();
        // result.put("count", queryCount);
        // result.put("elapsedTime", elapsedTime);
        // result.put("qps", queryCount / elapsedTime);

        return queryCounter.getQps();
    }
}
