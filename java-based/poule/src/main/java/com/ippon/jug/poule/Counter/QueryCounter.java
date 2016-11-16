package com.ippon.jug.poule.Counter;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jmonsinjon on 13/11/16.
 */
@Component
public class QueryCounter {
    private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    private NavigableMap<String, Integer> qps = new TreeMap<>();

    private Long startTime;
    private int totalCount;

    @PostConstruct
    private void initTimer(){
        startTime = Calendar.getInstance().getTimeInMillis();
    }

    public void addQuery() {
        String currentDate = formatter.format(Calendar.getInstance().getTime());
        Integer count;

        if(qps.containsKey(currentDate)) {
            count = qps.get(currentDate) + 1;
        } else {
            count = 1;
        }
        qps.put(currentDate, count);

        totalCount++;
    }

    public void purgeOlderQpsValues() {
        // TODO : Implémenter la méthode pour ne conserver que les 600 derniers points (10mn)
    }

    public Map<String, Integer> getQps() {
        return qps;
    }

    public Integer getLastSecCount() {
        Map.Entry<String, Integer> lastEntry = qps.lastEntry();
        if (lastEntry != null) {
            return lastEntry.getValue();
        } else {
            return 0;
        }
    }

    public Float getQpsSinceStarted() {
        return (totalCount / ((Calendar.getInstance().getTimeInMillis() - startTime) / 1000F));
    }
}
