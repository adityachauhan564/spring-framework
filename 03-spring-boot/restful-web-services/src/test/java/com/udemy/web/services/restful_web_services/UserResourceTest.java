package com.udemy.web.services.restful_web_services;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.udemy.web.services.restful_web_services.topic03_crud_resource.UserResource;
import com.udemy.web.services.restful_web_services.topic03_crud_resource.UserStore;
import com.udemy.web.services.restful_web_services.topic09_security.SecurityConfig;

/*
 * Topic    : Testing a REST controller (topic08)
 * Key idea : - @WebMvcTest starts ONLY the web layer: this controller, the @ControllerAdvice classes
 *              and the MVC configuration. Not the whole app, so it is fast.
 *            - Anything else it needs is added by hand with @Import
 *              (or replaced by a mock - a fake stand-in - with @MockitoBean).
 *            - MockMvc sends requests without a real server. jsonPath checks the JSON response.
 *            - Like testing only the car's brakes on a test bench, without driving the whole car.
 * Gotcha   : Spring reuses one context for all tests in a class, so the in-memory UserStore is
 *            SHARED. A test that deletes user 1 breaks a later test that counts users.
 *            Tests must never depend on their order: @DirtiesContext gives each test a fresh store.
 */
@WebMvcTest(UserResource.class)
@Import({UserStore.class, SecurityConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserResourceTest {

    private static final String VALID_USER = "{\"name\":\"Asha\",\"birthDate\":\"2000-01-01\"}";

    @Autowired
    MockMvc mvc;

    @Test
    void listIsPublic() throws Exception {
        mvc.perform(get("/users")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void missingUserIs404ProblemDetail() throws Exception {
        mvc.perform(get("/users/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("User not found"))
                .andExpect(jsonPath("$.detail").value("No user with id 99"));
    }

    @Test
    void createNeedsALogin() throws Exception {
        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(VALID_USER))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createReturns201AndLocation() throws Exception {
        mvc.perform(post("/users").with(httpBasic("admin", "admin123")).contentType(MediaType.APPLICATION_JSON).content(VALID_USER))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/users/4")))
                .andExpect(jsonPath("$.name").value("Asha"));
    }

    @Test
    void invalidBodyIs400WithEveryFieldError() throws Exception {
        mvc.perform(post("/users").with(httpBasic("admin", "admin123")).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"A\",\"birthDate\":\"2999-01-01\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.name").exists())
                .andExpect(jsonPath("$.fieldErrors.birthDate").value("birthDate must be in the past"));
    }

    @Test
    void readerMayNotDelete() throws Exception {
        mvc.perform(delete("/users/1").with(httpBasic("reader", "reader123"))).andExpect(status().isForbidden());
    }

    @Test
    void adminCanReplaceAndDelete() throws Exception {
        mvc.perform(put("/users/1").with(httpBasic("admin", "admin123")).contentType(MediaType.APPLICATION_JSON).content(VALID_USER))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.name").value("Asha"));
        mvc.perform(delete("/users/1").with(httpBasic("admin", "admin123"))).andExpect(status().isNoContent());
        mvc.perform(get("/users/1")).andExpect(status().isNotFound());
    }
}
