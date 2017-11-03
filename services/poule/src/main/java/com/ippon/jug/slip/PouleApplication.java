package com.ippon.jug.slip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PouleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PouleApplication.class, args);
    }
}

