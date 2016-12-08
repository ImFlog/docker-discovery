package com.ippon.jug.poule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PouleApplication {

    public static void main(String[] args) {
        System.setProperty("http.keepAlive", "false");
        SpringApplication.run(PouleApplication.class, args);
    }
}