package com.hfahimi.currencyconverterrest.convert;

public class ConvertResponse {
    Boolean success = false;
    ConvertCredential convertCredential;
    String date;
    Float calculatedRate;
    Float result;

    public ConvertResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ConvertCredential getConvertCredential() {
        return convertCredential;
    }

    public void setConvertCredential(ConvertCredential convertCredential) {
        this.convertCredential = convertCredential;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getCalculatedRate() {
        return calculatedRate;
    }

    public void setCalculatedRate(Float calculatedRate) {
        this.calculatedRate = calculatedRate;
    }

    public Float getResult() {
        return result;
    }

    public void setResult(Float result) {
        this.result = result;
    }
}

