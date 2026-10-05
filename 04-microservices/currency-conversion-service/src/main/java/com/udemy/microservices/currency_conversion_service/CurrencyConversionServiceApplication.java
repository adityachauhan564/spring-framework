package com.udemy.microservices.currency_conversion_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * @EnableFeignClients: find the @FeignClient interfaces (CurrencyExchangeProxy) and write their
 * code at startup. Without it, the proxy bean simply does not exist.
 */
@SpringBootApplication
@EnableFeignClients
public class CurrencyConversionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionServiceApplication.class, args);
    }
}
