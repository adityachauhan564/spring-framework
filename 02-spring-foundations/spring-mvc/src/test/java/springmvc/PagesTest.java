package springmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

/*
 * MockMvc sends requests through the real DispatcherServlet, without starting a server.
 * It doesn't render JSPs, so tests check the view name, the model and the status instead.
 */
@SpringJUnitWebConfig(WebMvcConfig.class)          // the same config the server loads
@TestPropertySource(properties = "DB_URL=")          // always in-memory H2
class PagesTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void homePutsDataInTheModelAndPicksTheIndexView() throws Exception {
        mvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/views/index.jsp"))
                .andExpect(model().attribute("name", "Roshan Chauhan"));
    }

    @Test
    void modelAndViewCarriesDataAndViewName() throws Exception {
        mvc.perform(get("/help"))
                .andExpect(view().name("help"))
                .andExpect(model().attributeExists("marks", "time"));
    }

    @Test
    void requestParamPathVariableAndModelAttribute() throws Exception {
        mvc.perform(get("/greet")).andExpect(model().attribute("value", "Hello, guest!"));
        mvc.perform(get("/students/7")).andExpect(model().attribute("source", "@PathVariable id"));
        mvc.perform(get("/search").param("city", "Pune").param("minAge", "18"))
                .andExpect(model().attribute("value", "SearchQuery[city=Pune, minAge=18]"));
    }

    @Test
    void wrongParameterTypeIsA400NotA500() throws Exception {
        mvc.perform(get("/students/abc")).andExpect(status().isBadRequest());
    }

    @Test
    void unknownUserShowsThe404ErrorPage() throws Exception {
        mvc.perform(get("/users/999"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"))
                .andExpect(model().attribute("message", "No user with id 999"));
    }

    @Test
    void unexpectedErrorShowsAGeneric500Page() throws Exception {
        mvc.perform(get("/error-demo"))
                .andExpect(status().isInternalServerError())
                .andExpect(view().name("error"))
                .andExpect(model().attribute("message", "Sorry, something went wrong. Please try again later."));
    }
}
