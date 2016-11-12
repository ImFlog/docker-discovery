package com.ippon.jug.poule;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public float getMeanQps() {
        return queryCount / ((System.currentTimeMillis() - startTime) / 1000F);
    }
}
