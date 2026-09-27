package com.udemy.microservices.currency_conversion_service;

import java.math.BigDecimal;

/*
 * The JSON that currency-exchange-service returns, as seen by THIS service.
 * Each microservice keeps its own copy of the shapes it uses - services share an API
 * contract, not Java classes (sharing code would couple their release cycles).
 */
public record ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple, String environment) {
}
