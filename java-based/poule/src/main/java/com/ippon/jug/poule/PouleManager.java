package com.ippon.jug.poule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    // TODO : clean the thread management
    @Override
    public void run(String... strings) throws Exception {
        while (true) {
            try {
                if (threadPoolExecutor.getActiveCount() < 10) {
                    threadPoolExecutor.execute(() -> {
                        String s = slipClient.getRequest();
                        // We increment the counter after feign call.
                        qpsCounter.addQuery();
                    });
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

    }

    @PreDestroy
    public void killThreadPool() {
        threadPoolExecutor.shutdown();
    }
}