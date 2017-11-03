package com.ippon.jug.slip.Schedduler;

import com.ippon.jug.slip.Counter.QueryCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by jmonsinjon on 13/11/16.
 */
@Component
@EnableScheduling
public class CotcotSchedduler {

    private static final int THREAD_POOL_SIZE = 10;

    private ThreadPoolExecutor threadPoolExecutor;
    private QueryCounter queryCounter;
    private SlipClient slipClient;


    @Autowired
    public CotcotSchedduler(QueryCounter queryCounter, SlipClient slipClient) {
        this.queryCounter = queryCounter;
        this.slipClient = slipClient;
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    @Scheduled(fixedDelay = 100)
    public void fillQueue(){
        while (threadPoolExecutor.getActiveCount() < THREAD_POOL_SIZE){
            threadPoolExecutor.execute(new CotCotWorker(queryCounter, slipClient));
        }
    }

    @PreDestroy
    public void killExecutor() {
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
        }
    }
}
