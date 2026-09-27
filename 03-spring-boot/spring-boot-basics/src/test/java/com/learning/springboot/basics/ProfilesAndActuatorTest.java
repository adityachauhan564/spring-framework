package com.learning.springboot.basics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.learning.springboot.basics.topic03_profiles.FakePaymentGateway;
import com.learning.springboot.basics.topic03_profiles.PaymentGateway;
import com.learning.springboot.basics.topic03_profiles.RealPaymentGateway;

class ProfilesAndActuatorTest {

    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    class DefaultProfile {

        @Autowired
        PaymentGateway gateway;

        @Autowired
        MockMvc mvc;

        @Test
        void defaultProfileUsesTheFakeGateway() {
            assertInstanceOf(FakePaymentGateway.class, gateway);
        }

        @Test
        void healthIncludesTheCustomShopIndicator() throws Exception {
            String body = mvc.perform(get("/actuator/health")).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            assertTrue(body.contains("\"shop\""));
        }

        @Test
        void customMetricCountsOrders() throws Exception {
            mvc.perform(post("/orders")).andExpect(status().isOk());
            String body = mvc.perform(get("/actuator/metrics/shop.orders.placed")).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            assertTrue(body.contains("COUNT"));
        }

        @Test
        void unexposedEndpointsAreNotReachable() throws Exception {
            mvc.perform(get("/actuator/env")).andExpect(status().isNotFound());
        }
    }

    @Nested
    @SpringBootTest
    @ActiveProfiles("prod")
    class ProdProfile {

        @Autowired
        PaymentGateway gateway;

        @Value("${app.environment-label}")
        String label;

        @Test
        void prodProfileSwapsBeansAndOverridesProperties() {
            assertInstanceOf(RealPaymentGateway.class, gateway);
            assertEquals("PRODUCTION", label);
        }
    }
}
