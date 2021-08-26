package com.hfahimi.currencyconverterrest.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumeSucceed implements Serializable {
    private Boolean success;
    private Long timestamp;
    private String base;
    private Date date;
    private Map<String, Float> rates;

    public ConsumeSucceed() {
    }


    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRates(Map<String, Float> rates) {
        this.rates = rates;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public String getBase() {
        return this.base;
    }

    public Date getDate() {
        return this.date;
    }

    public Map<String, Float> getRates() {
        return this.rates;
    }

    @Override
    public String toString() {
        return "ResponseSucceed{" +
                "success='" + success + '\'' +
                ", timestamp=" + timestamp +
                ", base='" + base + '\'' +
                ", date=" + date +
                ", rates=" + rates +
                '}';
    }
}
