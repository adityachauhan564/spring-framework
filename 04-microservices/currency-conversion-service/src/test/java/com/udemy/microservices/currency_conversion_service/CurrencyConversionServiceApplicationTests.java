package com.udemy.microservices.currency_conversion_service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import feign.FeignException;

/*
 * The Feign proxy is replaced by a mock (a fake), so no currency-exchange-service is needed.
 * The test decides what the remote service "returns", and checks our side of the contract.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CurrencyConversionServiceApplicationTests {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    CurrencyExchangeProxy proxy;

    @Test
    void feignEndpointMultipliesQuantityByTheRate() throws Exception {
        when(proxy.retrieveExchangeValue("USD", "INR"))
                .thenReturn(new ExchangeValue(10001L, "USD", "INR", new BigDecimal("91"), "8000"));
        mvc.perform(get("/currency-conversion-feign/from/USD/to/INR/quantity/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCalculatedAmount").value(910))
                .andExpect(jsonPath("$.environment").value("8000 feign"));
    }

    @Test
    void unknownPairFromTheExchangeServiceIs404() throws Exception {
        when(proxy.retrieveExchangeValue("USD", "XYZ")).thenThrow(mock(FeignException.NotFound.class));
        mvc.perform(get("/currency-conversion-feign/from/USD/to/XYZ/quantity/1")).andExpect(status().isNotFound());
    }

    @Test
    void exchangeServiceDownIs503() throws Exception {
        when(proxy.retrieveExchangeValue("USD", "INR")).thenThrow(mock(FeignException.ServiceUnavailable.class));
        mvc.perform(get("/currency-conversion-feign/from/USD/to/INR/quantity/1")).andExpect(status().isServiceUnavailable());
    }
}
