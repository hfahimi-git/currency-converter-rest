package com.hfahimi.currencyconverterrest.convert;

public class ConvertCredential {
    String source;
    String target;
    Float amount;

    public ConvertCredential() {
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public ConvertCredential(String source, String target, Float amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public Float getAmount() {
        return amount;
    }
}
