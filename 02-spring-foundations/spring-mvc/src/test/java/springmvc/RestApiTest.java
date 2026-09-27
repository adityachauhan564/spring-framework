package springmvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import springmvc.topic01_dispatcher_and_config.WebMvcConfig;

@SpringJUnitWebConfig(WebMvcConfig.class)
@TestPropertySource(properties = "DB_URL=")
class RestApiTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    private String json(String userName, String email, String password) {
        return "{\"userName\":\"" + userName + "\",\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
    }

    @Test
    void createReturns201WithLocationAndNoPasswordData() throws Exception {
        String body = mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(json("Meera", "meera@example.com", "secret123")))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn().getResponse().getContentAsString();
        assertTrue(body.contains("\"email\":\"meera@example.com\""));
        assertFalse(body.toLowerCase().contains("password"));

        String list = mvc.perform(get("/api/users")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertTrue(list.contains("meera@example.com"));
        assertFalse(list.toLowerCase().contains("password"));
    }

    @Test
    void invalidBodyIs400WithFieldErrors() throws Exception {
        String body = mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(json("", "x", "1")))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andReturn().getResponse().getContentAsString();
        assertTrue(body.contains("\"email\":\"that is not a valid email address\""));
    }

    @Test
    void unknownIdIs404ProblemJsonNotAnHtmlPage() throws Exception {
        mvc.perform(get("/api/users/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON));
    }

    @Test
    void duplicateEmailIs409() throws Exception {
        mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(json("Kiran", "kiran@example.com", "secret123")))
                .andExpect(status().isCreated());
        mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(json("Kiran2", "kiran@example.com", "secret123")))
                .andExpect(status().isConflict());
    }

    @Test
    void malformedJsonIs400() throws Exception {
        mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content("{bad"))
                .andExpect(status().isBadRequest());
    }
}
