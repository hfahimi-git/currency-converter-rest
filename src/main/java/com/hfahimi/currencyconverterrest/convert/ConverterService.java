package com.hfahimi.currencyconverterrest.convert;

public interface ConverterService {
    ConvertResponse convert (ConvertCredential convertCredential) throws Exception;
}
