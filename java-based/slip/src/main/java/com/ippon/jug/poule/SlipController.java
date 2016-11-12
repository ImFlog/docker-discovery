package com.ippon.jug.poule;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlipController {

    @RequestMapping(value = "/request")
    public String getRequest() throws InterruptedException {
        this.doSlip();
        return "Spring and Spring cloud are Awesome !!";
    }

    private static final int SECOND = 1000;

    private synchronized void doSlip() throws InterruptedException {
        Thread.sleep(SECOND);
    }
}
