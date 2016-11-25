package com.ippon.jug.poule.Schedduler;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "slip", url = "http://52.213.226.162:8080")
interface SlipClient {

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    String getRequest();
}