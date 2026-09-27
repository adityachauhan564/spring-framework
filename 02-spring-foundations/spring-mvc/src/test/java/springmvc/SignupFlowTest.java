package springmvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import springmvc.topic01_dispatcher_and_config.WebMvcConfig;
import springmvc.topic04_service_and_dao_layers.User;
import springmvc.topic04_service_and_dao_layers.UserService;

@SpringJUnitWebConfig(WebMvcConfig.class)
@TestPropertySource(properties = "DB_URL=")
class SignupFlowTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    UserService userService;

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void invalidFormShowsTheSameFormWithErrors() throws Exception {
        mvc.perform(post("/processform").param("email", "nope").param("userName", "").param("password", "short"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"))
                .andExpect(model().attributeHasFieldErrors("form", "email", "userName", "password"));
    }

    @Test
    void validFormRedirectsAndStoresOnlyAPasswordHash() throws Exception {
        mvc.perform(post("/processform").param("email", "asha@example.com").param("userName", "Asha").param("password", "secret123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/success"))
                .andExpect(flash().attributeExists("user"));

        User saved = userService.findAll().stream().filter(u -> u.getEmail().equals("asha@example.com")).findFirst().orElseThrow();
        assertFalse(saved.getPasswordHash().contains("secret123"));
        assertTrue(saved.getPasswordHash().startsWith("$2a$"));                // BCrypt format
        assertTrue(userService.checkPassword(saved.getId(), "secret123"));
    }

    @Test
    void duplicateEmailIsAFieldError() throws Exception {
        userService.register("Ravi", "ravi@example.com", "secret123");
        mvc.perform(post("/processform").param("email", "ravi@example.com").param("userName", "Ravi2").param("password", "secret123"))
                .andExpect(view().name("contact"))
                .andExpect(model().attributeHasFieldErrorCode("form", "email", "duplicate"));
    }

    @Test
    void successPageWithoutFlashDataRedirectsToTheForm() throws Exception {
        mvc.perform(get("/success")).andExpect(redirectedUrl("/contact"));
    }
}
