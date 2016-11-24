package com.ippon.jug.poule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PouleApplication {

    public static void main(String[] args) {
        System.setProperty("networkaddress.cache.ttl", "5");
        SpringApplication.run(PouleApplication.class, args);
    }
}

