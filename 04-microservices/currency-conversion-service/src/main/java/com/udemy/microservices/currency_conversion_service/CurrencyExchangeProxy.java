package com.udemy.microservices.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * Topic    : Declarative HTTP clients with OpenFeign
 * Key idea : - Describe the other service's endpoint with an INTERFACE (using the Spring MVC
 *              annotations you already know), and Feign writes the HTTP client code at startup.
 *            - Like ordering on Swiggy: you only say WHAT you want, the app handles HOW it reaches you.
 *            - name = "currency-exchange" must be the same as the target's spring.application.name.
 *              Feign asks Eureka for that name, and shares the calls across every instance.
 *            - No host, no port, no URL building anywhere.
 */
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
