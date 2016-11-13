package com.ippon.jug.poule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class PouleManager implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(PouleManager.class);

    private static final int THREAD_POOL_SIZE = 10;

    private SlipClient slipClient;
    private QPSCounterController qpsCounter;

    private ThreadPoolExecutor threadPoolExecutor;

    public PouleManager(SlipClient slipClient, QPSCounterController qpsCounter) {
        this.slipClient = slipClient;
        this.qpsCounter = qpsCounter;
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    @Override
    public void run(String... strings) throws Exception {
        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            logger.info("Starting thread executors {}", i);
            threadPoolExecutor.execute(() -> {
                while (true) {
                    try {
                        String s = slipClient.getRequest();
                        // We increment the counter after feign call.
                        qpsCounter.addQuery();
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            });
        }
        logger.info("All threads started");
    }

    @PreDestroy
    public void killExecutor() {
        threadPoolExecutor.shutdown();
    }
}