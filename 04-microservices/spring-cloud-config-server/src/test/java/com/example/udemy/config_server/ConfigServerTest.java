package com.example.udemy.config_server;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/*
 * Asks the server for config exactly as a client would: GET /{application}/{profile}.
 * Maven runs tests from this module's folder, so ../git-local-config-repo is found.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ConfigServerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void devProfileFileComesFirstAndWins() throws Exception {
        mvc.perform(get("/limit-service-microservices/dev"))
                .andExpect(status().isOk())
                // property sources are listed highest priority first: the -dev file, then the base file
                .andExpect(jsonPath("$.propertySources[0].name", containsString("limit-service-microservices-dev.properties")))
                .andExpect(jsonPath("$.propertySources[0].source['limits-service.minimum']").value("5"))
                .andExpect(jsonPath("$.propertySources[1].source['limits-service.minimum']").value("4"));
    }

    @Test
    void qaProfileIsServedToo() throws Exception {
        mvc.perform(get("/limit-service-microservices/qa"))
                .andExpect(jsonPath("$.propertySources[0].source['limits-service.maximum']").value("994"));
    }
}
