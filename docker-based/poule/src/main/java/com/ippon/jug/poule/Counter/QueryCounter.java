package com.ippon.jug.poule.Counter;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jmonsinjon on 13/11/16.
 */
@Component
public class QueryCounter {
    private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    private static Integer NB_ELEMENTS_IN_QUEUE = 600;
    private NavigableMap<String, Integer> qps = new TreeMap<>();
    private Map.Entry<String, Integer> currentCounter;

    private Long startTime;
    private int totalCount;
    private ConcurrentHashMap<String, String> hostMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void initTimer() {
        startTime = Calendar.getInstance().getTimeInMillis();
        currentCounter = new AbstractMap.SimpleEntry<>(formatter.format(Calendar.getInstance().getTime()), 0);
    }

    public void addQuery() {
        String currentDate = formatter.format(Calendar.getInstance().getTime());

        if (currentCounter.getKey().equals(currentDate)) {
            currentCounter.setValue(currentCounter.getValue() + 1);
        } else {
            qps.put(currentCounter.getKey(), currentCounter.getValue());
            currentCounter = new AbstractMap.SimpleEntry<>(currentDate, 1);
            purgeOlderQpsValues();
        }

        totalCount++;
    }

    private void purgeOlderQpsValues() {
        if (qps.size() > NB_ELEMENTS_IN_QUEUE) {
            qps.remove(qps.firstKey());
        }
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

    public ConcurrentHashMap<String, String> getHostMap() {
        return hostMap;
    }
}
