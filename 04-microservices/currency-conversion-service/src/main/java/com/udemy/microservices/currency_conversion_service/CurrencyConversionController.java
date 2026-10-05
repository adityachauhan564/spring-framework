package com.udemy.microservices.currency_conversion_service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

/*
 * Topic    : One service calling another - two ways
 *   /currency-conversion/...       RestClient: you build the request yourself (the modern
 *                                  replacement for RestTemplate, which the course uses and is now old)
 *   /currency-conversion-feign/... Feign: you only wrote an interface (CurrencyExchangeProxy)
 *   Both find "currency-exchange" through Eureka and share calls between its instances,
 *   so neither has a port number written into the code.
 * Try this : curl localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
 *            curl localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
 *            then stop currency-exchange-service and call again -> 503 (ConversionExceptionHandler)
 */
@RestController
public class CurrencyConversionController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyConversionController.class);

    private final RestClient restClient;
    private final CurrencyExchangeProxy proxy;

    public CurrencyConversionController(RestClient currencyExchangeRestClient, CurrencyExchangeProxy proxy) {
        this.restClient = currencyExchangeRestClient;      // load-balanced (spreads calls across instances), see RestClientConfig
        this.proxy = proxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertWithRestClient(@PathVariable String from, @PathVariable String to,
                                                    @PathVariable BigDecimal quantity) {
        log.info("convert {} {} to {} with RestClient", quantity, from, to);
        ExchangeValue exchange = restClient.get()
                .uri("/currency-exchange/from/{from}/to/{to}", from, to)
                .retrieve()
                .body(ExchangeValue.class);
        return CurrencyConversion.of(exchange, quantity, "rest-client");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertWithFeign(@PathVariable String from, @PathVariable String to,
                                               @PathVariable BigDecimal quantity) {
        log.info("convert {} {} to {} with Feign", quantity, from, to);
        return CurrencyConversion.of(proxy.retrieveExchangeValue(from, to), quantity, "feign");
    }
}
