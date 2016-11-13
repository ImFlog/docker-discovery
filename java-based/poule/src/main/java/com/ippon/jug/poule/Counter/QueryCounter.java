package com.ippon.jug.poule.Counter;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jmonsinjon on 13/11/16.
 */
@Component
public class QueryCounter {
    private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    private Map<String, Integer> qps = new TreeMap<String, Integer>();

    public void addQuery() {
        String currentDate = formatter.format(Calendar.getInstance().getTime());
        Integer count;

        if(qps.containsKey(currentDate)) {
            count = qps.get(currentDate) + 1;
        } else {
            count = 1;
        }
        qps.put(currentDate, count);
    }

    public void purgeOlderQpsValues() {
        // TODO : Implémenter la méthode pour ne conserver que les 600 derniers points (10mn)
    }

    public Map<String, Integer> getQps() {
        return qps;
    }
}
