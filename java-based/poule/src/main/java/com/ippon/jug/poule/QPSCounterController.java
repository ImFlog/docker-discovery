package com.ippon.jug.poule;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class QPSCounterController {

    private Long queryCount = 0L;
    private Long startTime = null;

    public void addQuery() {
        if (startTime == null) {
            this.startTime = System.currentTimeMillis();
        }
        queryCount++;
    }

    @RequestMapping(value = "/qps")
    public Map<String, Object> getMeanQps() {
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000F;
        Map<String, Object> result = new HashMap<>();
        result.put("count", queryCount);
        result.put("elapsedTime", elapsedTime);
        result.put("qps", queryCount / elapsedTime);
        return result;
    }
}
