package com.hfahimi.currencyconverterrest;

import io.github.sercasti.tracing.config.TracingConfig;
import io.github.sercasti.tracing.core.Tracing;
import io.github.sercasti.tracing.filter.TracingFilter;
import io.github.sercasti.tracing.interceptor.TracingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CurrencyConverterRestApplication {
    private static final Logger log = LoggerFactory.getLogger(CurrencyConverterRestApplication.class);
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    //server-timing stuff
    @Bean
    protected Tracing tracing() {
        return TracingConfig.createTracing();
    }

    @Bean
    protected TracingFilter tracingFilter() {
        return new TracingFilter();
    }

    @Bean
    protected TracingInterceptor tracingInterceptor() {
        return new TracingInterceptor(tracing());
    }

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConverterRestApplication.class, args);
    }

}
