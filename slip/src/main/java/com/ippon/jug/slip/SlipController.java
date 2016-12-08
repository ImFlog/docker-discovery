package com.ippon.jug.slip;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class SlipController {


    private static final int SLEEP_MILLIS = 100;

    @RequestMapping(value = "/request")
    public String getRequest() throws InterruptedException, UnknownHostException {
        this.doSlip();
        return "Hello I'm " + InetAddress.getLocalHost().getHostName();
    }

    private synchronized void doSlip() throws InterruptedException {
        Thread.sleep(SLEEP_MILLIS);
    }
}
