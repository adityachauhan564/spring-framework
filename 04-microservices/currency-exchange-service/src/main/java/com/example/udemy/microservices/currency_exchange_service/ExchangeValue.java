package com.example.udemy.microservices.currency_exchange_service;

import java.math.BigDecimal;

/*
 * The response: the rate plus "environment" = which INSTANCE answered (its port).
 * Run two instances and call through the gateway several times: environment alternates,
 * which shows Eureka + the load balancer spreading the calls.
 */
public record ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple, String environment) {

    static ExchangeValue of(CurrencyExchange exchange, String environment) {
        return new ExchangeValue(exchange.getId(), exchange.getFrom(), exchange.getTo(), exchange.getConversionMultiple(), environment);
    }
}
