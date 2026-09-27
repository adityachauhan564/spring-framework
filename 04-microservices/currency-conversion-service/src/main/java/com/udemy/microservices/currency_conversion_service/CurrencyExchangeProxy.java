package com.udemy.microservices.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * Topic    : Declarative HTTP clients with OpenFeign
 * Key idea : describe the remote endpoint with an INTERFACE (Spring MVC annotations you
 *            already know) and Feign writes the HTTP client at startup.
 *            name = "currency-exchange" must equal the target's spring.application.name:
 *            Feign asks Eureka for that name and load-balances across every instance.
 *            No host, no port, no URL building anywhere.
 */
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
