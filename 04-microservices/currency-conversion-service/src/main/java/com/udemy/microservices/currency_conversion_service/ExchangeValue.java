package com.udemy.microservices.currency_conversion_service;

import java.math.BigDecimal;

/*
 * The JSON that currency-exchange-service returns, as seen by THIS service.
 * Each microservice keeps its own copy of the shapes it uses. Services share an API
 * contract (an agreed JSON shape), not Java classes - sharing code would force them
 * to release new versions together.
 */
public record ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple, String environment) {
}
