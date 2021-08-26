package com.hfahimi.currencyconverterrest.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class ConsumeServiceFileImpl implements ConsumeService {
    @Value("${file.url}")
    private String fileUrl;

    public ConsumeSucceed consume() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ConsumeSucceed consumeSucceed = objectMapper.readValue(new ClassPathResource(fileUrl).getFile(), ConsumeSucceed.class);
        consumeSucceed.setRates(sanitizeRates(consumeSucceed.getRates()));
        return  consumeSucceed;
    }

}
