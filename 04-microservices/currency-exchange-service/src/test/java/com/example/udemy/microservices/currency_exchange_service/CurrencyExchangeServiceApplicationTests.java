package com.example.udemy.microservices.currency_exchange_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

/* The whole service with H2 and data.sql (Eureka and the config server are off in tests). */
@SpringBootTest(properties = "local.server.port=8000")
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CurrencyExchangeServiceApplicationTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    CurrencyExchangeRepository repository;

    @Autowired
    CircuitBreakerController resilience;

    @Test
    void seedDataIsLoadedAndFoundByTheDerivedQuery() {
        CurrencyExchange usdInr = repository.findByFromAndTo("USD", "INR").orElseThrow();
        assertEquals(0, new BigDecimal("91").compareTo(usdInr.getConversionMultiple()));
        assertTrue(repository.findByFromAndTo("USD", "XYZ").isEmpty());
    }

    @Test
    void endpointReturnsTheRateAndTheAnsweringInstance() throws Exception {
        mvc.perform(get("/currency-exchange/from/EUR/to/INR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.from").value("EUR"))
                .andExpect(jsonPath("$.conversionMultiple").value(113.00))
                .andExpect(jsonPath("$.environment").value("8000"));
    }

    @Test
    void unknownPairIs404() throws Exception {
        mvc.perform(get("/currency-exchange/from/USD/to/XYZ")).andExpect(status().isNotFound());
    }

    @Test
    void retryEndsInTheFallbackWhenTheDependencyIsDown() {
        assertTrue(resilience.retryDemo().startsWith("fallback-response"));
    }
}
