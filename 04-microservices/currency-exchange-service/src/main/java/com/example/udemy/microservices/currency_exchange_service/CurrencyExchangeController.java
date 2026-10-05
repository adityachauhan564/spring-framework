package com.example.udemy.microservices.currency_exchange_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/*
 * Try this : curl localhost:8000/currency-exchange/from/USD/to/INR
 *            curl -i localhost:8000/currency-exchange/from/USD/to/XYZ   -> 404 (the course returned a 500)
 * The log line carries [traceId,spanId]. Call through the conversion service or the gateway, and
 * the SAME traceId appears in their logs too. This is distributed tracing -
 * like a courier tracking number that follows one parcel through every hub.
 */
@RestController
public class CurrencyExchangeController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private final CurrencyExchangeRepository repository;
    private final Environment environment;

    public CurrencyExchangeController(CurrencyExchangeRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        log.info("retrieveExchangeValue called with {} to {}", from, to);
        CurrencyExchange exchange = repository.findByFromAndTo(from.toUpperCase(), to.toUpperCase())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No exchange rate from " + from + " to " + to));
        // local.server.port is the port that THIS running copy of the service actually listens on
        return ExchangeValue.of(exchange, environment.getProperty("local.server.port"));
    }
}
