package com.udemy.web.services.restful_web_services;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/*
 * @SpringBootTest starts the WHOLE application. It is slower than @WebMvcTest, but it proves
 * that all the pieces fit together. One or two of these per app is usually enough.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RestfulWebServicesApplicationTests {

    @Autowired
    MockMvc mvc;

    @Test
    void wholeAppStartsAndServesJson() throws Exception {
        mvc.perform(get("/hello-world-bean")).andExpect(status().isOk()).andExpect(jsonPath("$.message").value("Hello World"));
    }

    @Test
    void openApiDocsArePublic() throws Exception {
        mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info.title").value("RESTful Web Services - learning API"));
    }
}
