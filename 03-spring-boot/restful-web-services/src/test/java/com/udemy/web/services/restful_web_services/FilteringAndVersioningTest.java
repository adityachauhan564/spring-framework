package com.udemy.web.services.restful_web_services;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.udemy.web.services.restful_web_services.topic05_filtering_and_dtos.FilteringController;
import com.udemy.web.services.restful_web_services.topic06_versioning.VersioningController;
import com.udemy.web.services.restful_web_services.topic09_security.SecurityConfig;

@WebMvcTest({FilteringController.class, VersioningController.class})
@Import(SecurityConfig.class)
class FilteringAndVersioningTest {

    @Autowired
    MockMvc mvc;

    @Test
    void jsonIgnoreHidesThePasswordHash() throws Exception {
        mvc.perform(get("/accounts/1")).andExpect(jsonPath("$.passwordHash").doesNotExist()).andExpect(jsonPath("$.email").exists());
    }

    @Test
    void jsonViewPublicShowsOnlyPublicFields() throws Exception {
        mvc.perform(get("/accounts/1/public"))
                .andExpect(jsonPath("$.owner").value("Asha"))
                .andExpect(jsonPath("$.email").doesNotExist())
                .andExpect(jsonPath("$.balance").doesNotExist());
    }

    @Test
    void noVersionHeaderMeansVersion1() throws Exception {
        mvc.perform(get("/person")).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Bob Charlie"));
    }

    @Test
    void headerSelectsVersion2() throws Exception {
        mvc.perform(get("/person").header("X-API-Version", "2")).andExpect(jsonPath("$.name.firstName").value("Bob"));
    }

    @Test
    void unsupportedVersionIs400() throws Exception {
        mvc.perform(get("/person").header("X-API-Version", "7")).andExpect(status().isBadRequest());
    }
}
