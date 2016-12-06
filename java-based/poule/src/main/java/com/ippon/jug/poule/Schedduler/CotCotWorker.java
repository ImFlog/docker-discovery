package com.ippon.jug.poule.Schedduler;

import com.ippon.jug.poule.Counter.QueryCounter;

/**
 * Created by jmonsinjon on 13/11/16.
 */
public class CotCotWorker implements Runnable {

    private QueryCounter queryCounter;

    public CotCotWorker(QueryCounter queryCounter) {
        this.queryCounter = queryCounter;
    }

    @Override
    public void run() {
        // Do http call here
        String s = null;
        if (s != null) {
            queryCounter.addQuery();
        }
    }
}
