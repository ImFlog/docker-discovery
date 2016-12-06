package com.ippon.jug.slip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SlipApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlipApplication.class, args);
    }
}

