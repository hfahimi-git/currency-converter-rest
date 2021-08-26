package com.hfahimi.currencyconverterrest.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ConsumeServiceRestApiImpl implements ConsumeService {

    private final RestTemplate restTemplate;
    @Value("${rest.url}")
    private String retUrl;

    private static Date LAST_CALL_DATE = null;
    private static ConsumeSucceed LAST_CALL_RESULT = null;

    public ConsumeServiceRestApiImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ConsumeSucceed consume() throws ConsumeException, JsonProcessingException {
        ConsumeSucceed consumeSucceed;
        ResponseEntity<String> response;
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");

        if(Objects.isNull(LAST_CALL_DATE)) {
            response = restTemplate.getForEntity(retUrl, String.class);
        }
        else {
            HttpHeaders headers = new HttpHeaders();
            headers.set("If-Modified-Since", format.format(LAST_CALL_DATE));
            HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
            response = restTemplate.exchange(
                    retUrl,
                    HttpMethod.GET,
                    request,
                    String.class
            );
        }
        TimeZone.setDefault( TimeZone.getTimeZone("GMT"));
        LAST_CALL_DATE = new Date();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        try {
            LAST_CALL_DATE = format.parse(Objects.requireNonNull(response.getHeaders().get("Date")).get(0));
        } catch (ParseException ignore) { }

        //if http status is ok
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            JsonNode checkSuccess = root.path("success");
            //if result object has status field with false value
            if (!checkSuccess.asBoolean()) {
                throw new ConsumeException(root.path("error").path("message").asText());
            } else {
                consumeSucceed = mapper.treeToValue(root, ConsumeSucceed.class);
                consumeSucceed.setRates(sanitizeRates(consumeSucceed.getRates()));
                LAST_CALL_RESULT = consumeSucceed;
            }
        }
        // response 304, not modified from last request, read from cache
        else if(response.getStatusCode().equals(HttpStatus.NOT_MODIFIED)) {
            consumeSucceed = LAST_CALL_RESULT;
        }
        //if http status is not ok
        else {
            throw new ConsumeException(root.path("error").path("message").asText());
        }
        return consumeSucceed;
    }

}
