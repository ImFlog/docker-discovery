package com.ippon.jug.slip.Schedduler;

import com.ippon.jug.slip.Counter.QueryCounter;

/**
 * Created by jmonsinjon on 13/11/16.
 */
public class CotCotWorker implements Runnable {

    private QueryCounter queryCounter;
    private SlipClient slipClient;

    public CotCotWorker(QueryCounter queryCounter, SlipClient slipClient) {
        this.queryCounter = queryCounter;
        this.slipClient = slipClient;
    }

    @Override
    public void run() {
        String s = slipClient.getRequest();
        if (s != null) {
            queryCounter.addQuery();
        }
    }
}
