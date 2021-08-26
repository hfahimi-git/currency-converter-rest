package com.hfahimi.currencyconverterrest.convert;

import com.hfahimi.currencyconverterrest.consumer.ConsumeSucceed;
import com.hfahimi.currencyconverterrest.consumer.ConsumeService;
import io.github.sercasti.tracing.Traceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@Service
public class ConverterServiceImpl implements ConverterService {

    private final ConsumeService consumeServiceRestApiImpl;

    @Autowired
    public ConverterServiceImpl(ConsumeService consumeServiceRestApiImpl) {
        this.consumeServiceRestApiImpl = consumeServiceRestApiImpl;
    }

    private ConvertResponse prepareResponse(ConvertCredential convertCredential){
        ConvertResponse response = new ConvertResponse();
        response.setConvertCredential(convertCredential);
        TimeZone.setDefault( TimeZone.getTimeZone("GMT"));
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");

        response.setDate(format.format(new Date()));
        return response;
    }

    @Traceable
    public ConvertResponse convert (ConvertCredential convertCredential) throws ConvertException {
        ConsumeSucceed consumeSucceed;
        try {
            consumeSucceed = consumeServiceRestApiImpl.consume();
        } catch (Exception e) {
            throw new ResponseStatusException(500, e.getMessage(), null);
        }

        ConvertResponse response = prepareResponse(convertCredential);
        Map<String, Float> rates = consumeSucceed.getRates();
        //add EUR in rates
        rates.put("eur", 1F);
        if(!rates.containsKey(convertCredential.getSource())) {
            throw new ConvertException("source currency symbol not found");
        }

        if(!rates.containsKey(convertCredential.getTarget())) {
            throw new ConvertException("target currency symbol not found");
        }

        float sourceRate = rates.get(convertCredential.getSource());
        float targetRate = rates.get(convertCredential.getTarget());
        float rate = (1 / sourceRate) * targetRate;
        response.setCalculatedRate(rate);
        response.setResult(convertCredential.getAmount() * rate);
        response.setSuccess(true);

        return response;

    }
}
