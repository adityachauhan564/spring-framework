package com.udemy.microservices.currency_conversion_service;

import java.math.BigDecimal;

/*
 * The response: the exchange rate from the other service, multiplied by the quantity.
 * "environment" says which currency-exchange INSTANCE answered, plus how it was called.
 */
public record CurrencyConversion(Long id, String from, String to, BigDecimal quantity,
                                 BigDecimal conversionMultiple, BigDecimal totalCalculatedAmount, String environment) {

    static CurrencyConversion of(ExchangeValue exchange, BigDecimal quantity, String calledWith) {
        return new CurrencyConversion(exchange.id(), exchange.from(), exchange.to(), quantity,
                exchange.conversionMultiple(), quantity.multiply(exchange.conversionMultiple()),
                exchange.environment() + " " + calledWith);
    }
}
