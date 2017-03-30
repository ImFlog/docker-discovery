package com.ippon.jug.poule;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class SlipController {

    @RequestMapping(value = "/request")
    public String getRequest() throws InterruptedException, UnknownHostException {
        this.doSlip();
        return "Hello I'm " + InetAddress.getLocalHost().getHostName();
    }

    private static final int SLEEP_MILLIS = 100;

    private synchronized void doSlip() throws InterruptedException {
        Thread.sleep(SLEEP_MILLIS);
    }

    @RequestMapping(value = "/dockerHealth")
    public int getHealth() {
        // 0 = OK
        // 1 = KO
        return 0;
    }
}
