package com.example.udemy.limit_service_microservices;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/*
 * No config server in tests: spring.config.import is switched off, so the service must fall
 * back to its own application.properties (3 / 997). The end-to-end check with the real config
 * server is in the stage README (start-all script).
 */
@SpringBootTest(properties = {"spring.config.import=", "spring.cloud.config.enabled=false"})
@AutoConfigureMockMvc
class LimitServiceMicroservicesApplicationTests {

    @Autowired
    MockMvc mvc;

    @Test
    void withoutTheConfigServerTheLocalFallbackIsUsed() throws Exception {
        mvc.perform(get("/limits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.minimum").value(3))
                .andExpect(jsonPath("$.maximum").value(997));
    }

    @Test
    void refreshEndpointIsExposed() throws Exception {
        mvc.perform(post("/actuator/refresh"))
                .andExpect(status().isOk());
    }
}
