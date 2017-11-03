package com.ippon.jug.slip;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class SlipController {

    @RequestMapping(value = "/request")
    public String getRequest() throws InterruptedException, UnknownHostException {
        this.doSlip();

        StringBuilder builder = new StringBuilder()
                .append("Hello I'm ")
                .append(InetAddress.getLocalHost().getHostName())
                .append(". My little secret is ... ");
        try {
            Files.readAllLines(Paths.get("/run/secrets/bdx"))
                    .forEach(builder::append);
        } catch (IOException e) {
            // No secret here
            builder.append(" UNKNOWN !");
        }
        return builder.toString();
    }

    private static final int SLEEP_MILLIS = 50;

    private synchronized void doSlip() throws InterruptedException {
        Thread.sleep(SLEEP_MILLIS);
    }

    @RequestMapping(value = "/dockerHealth")
    public ResponseEntity<Void> getHealth() {
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
