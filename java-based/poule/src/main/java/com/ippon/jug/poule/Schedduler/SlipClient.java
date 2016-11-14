package com.ippon.jug.poule.Schedduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "slip", fallback = SlipClientFallback.class)
interface SlipClient {

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    String getRequest();
}

class SlipClientFallback implements SlipClient {

    private Logger logger = LoggerFactory.getLogger(SlipClient.class);

    @Override
    public String getRequest() {
        logger.error("Could not reach slip");
        return null;
    }
}
