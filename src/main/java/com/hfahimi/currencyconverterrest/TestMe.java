package com.hfahimi.currencyconverterrest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class TestMe {
    public static void main(String[] args) {
/*
        RestTemplate restTemplate = new RestTemplate();
        ApiCallResponse res = new ApiCallResponse();
        // https://quoters.apps.pcfone.io/api/random
        try{
            ResponseEntity<String> response
                    = restTemplate.getForEntity("http://api.exchangeratesapi.io/v1/latest?access_key=b4bbac093b49ecae92821e9ca836b1d2", String.class);
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(response.getHeaders());
            JsonNode root = mapper.readTree(response.getBody());

            if(response.getStatusCode().equals(HttpStatus.OK)) {
                ResponseSucceed responseSucceed = mapper.treeToValue(root, ResponseSucceed.class);
                res.setSuccess(responseSucceed);
            }
            else {
                JsonNode error = root.path("error");
                ResponseError responseError = mapper.treeToValue(error, ResponseError.class);
                res.setError(responseError);
            }

        }
        catch(ResourceAccessException | JsonProcessingException e) {
            ResponseError responseError = new ResponseError();
            responseError.setCode("500");
            responseError.setMessage(e.getMessage());
            res.setError(responseError);
        }

        System.out.println(res);
*/

    }
}

