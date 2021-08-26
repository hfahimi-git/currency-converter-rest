package com.hfahimi.currencyconverterrest.consumer;


import java.util.HashMap;
import java.util.Map;

public interface ConsumeService {
    ConsumeSucceed consume()  throws Exception;

    default  Map<String, Float> sanitizeRates(Map<String, Float> rates) {
        Map<String, Float> temp = new HashMap<>();
        for (Map.Entry<String, Float> entry: rates.entrySet()) {
            temp.put(entry.getKey().toLowerCase(), entry.getValue());
        }
        return temp;

    }
}
