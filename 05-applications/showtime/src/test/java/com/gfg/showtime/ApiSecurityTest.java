package com.gfg.showtime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.gfg.showtime.repository.UserRepository;
import com.jayway.jsonpath.JsonPath;

/*
 * The API over HTTP (MockMvc), with the DataSeeder sample data and REAL logins: httpBasic(...) sends
 * the Authorization header, so the password is checked against the BCrypt hash just like in production.
 * Each test class uses its own named H2 database, so test classes with different setups never share data.
 */
@SpringBootTest(properties = "spring.datasource.url=jdbc:h2:mem:api-test")
@AutoConfigureMockMvc
class ApiSecurityTest {

    static final String ASHA = "asha@example.com", ASHA_PW = "password123";
    static final String ADMIN = "admin@showtime.local", ADMIN_PW = "admin12345";

    @Autowired MockMvc mvc;
    @Autowired UserRepository users;

    @Test
    void readingIsPublic() throws Exception {
        mvc.perform(get("/movie/1")).andExpect(jsonPath("$.title").value("Inception"));
        mvc.perform(get("/show/search").param("city", "MUMBAI")).andExpect(jsonPath("$", hasSize(2)));  // capital or small letters don't matter for city
        mvc.perform(get("/show/search").param("city", "Mumbai").param("movieName", "3 Idiots"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void addingAMovieNeedsAnAdmin() throws Exception {
        String movie = "{\"title\":\"Dangal\",\"genre\":\"DRAMA\"}";
        mvc.perform(json(post("/movie/add"), movie)).andExpect(status().isUnauthorized());                              // 401: who are you?
        mvc.perform(json(post("/movie/add"), movie).with(httpBasic(ASHA, "wrong-password"))).andExpect(status().isUnauthorized());
        mvc.perform(json(post("/movie/add"), movie).with(httpBasic(ASHA, ASHA_PW))).andExpect(status().isForbidden());  // 403: not allowed
        mvc.perform(json(post("/movie/add"), movie).with(httpBasic(ADMIN, ADMIN_PW))).andExpect(status().isCreated());
        mvc.perform(json(post("/movie/add"), movie).with(httpBasic(ADMIN, ADMIN_PW))).andExpect(status().isConflict());  // same title
    }

    @Test
    void signupStoresAHashAndCannotChooseItsRole() throws Exception {
        String body = """
                {"name":"Ravi","password":"secret-pass","mobile":"9111111111","email":"ravi@example.com","role":"ADMIN"}""";
        mvc.perform(json(post("/user/signup"), body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.role").value("USER"))                // the "role":"ADMIN" we sent was ignored
                .andExpect(jsonPath("$.password").doesNotExist());

        assertThat(users.findByEmail("ravi@example.com").orElseThrow().getPassword()).startsWith("$2");   // BCrypt
        mvc.perform(get("/user/me").with(httpBasic("ravi@example.com", "secret-pass"))).andExpect(jsonPath("$.name").value("Ravi"));
        mvc.perform(json(post("/user/signup"), body)).andExpect(status().isConflict());
    }

    @Test
    void validationErrorsNameEveryField() throws Exception {
        mvc.perform(json(post("/theater/add"), "{\"name\":\"\"}").with(httpBasic(ADMIN, ADMIN_PW)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").value("Name is mandatory"))
                .andExpect(jsonPath("$.errors.city").exists())
                .andExpect(jsonPath("$.errors.address").exists());
    }

    @Test
    void aTicketIsVisibleOnlyToItsOwnerAndAdmins() throws Exception {
        String ticket = mvc.perform(json(post("/ticket/book"), "{\"showId\":2,\"seatsNumbers\":[\"1C\"],\"seatType\":\"REGULAR\"}")
                        .with(httpBasic(ASHA, ASHA_PW)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        int id = JsonPath.read(ticket, "$.id");

        mvc.perform(json(post("/user/signup"), """
                {"name":"Other","password":"other-pass","mobile":"9222222222","email":"other@example.com"}"""));
        mvc.perform(get("/ticket/{id}", id).with(httpBasic("other@example.com", "other-pass"))).andExpect(status().isForbidden());
        mvc.perform(get("/ticket/{id}", id).with(httpBasic(ASHA, ASHA_PW))).andExpect(jsonPath("$.allottedSeats").value("1C"));
        mvc.perform(get("/ticket/{id}", id).with(httpBasic(ADMIN, ADMIN_PW))).andExpect(status().isOk());
    }

    @Test
    void reviewsSetTheAverageRatingAndTheTopFive() throws Exception {
        for (int rating : new int[] {5, 3}) {
            mvc.perform(json(post("/review/add"), "{\"movieId\":3,\"movieReview\":\"great\",\"rating\":" + rating + "}")
                    .with(httpBasic(ASHA, ASHA_PW))).andExpect(status().isCreated());
        }
        mvc.perform(get("/movie/3")).andExpect(jsonPath("$.rating").value(4.0)).andExpect(jsonPath("$.reviews", hasSize(2)));
        mvc.perform(get("/movie/top").param("genre", "SCI_FI"))
                .andExpect(jsonPath("$", hasSize(1)))                       // Inception has no reviews, so it has no rating yet
                .andExpect(jsonPath("$[0].title").value("Interstellar"));
    }

    private static MockHttpServletRequestBuilder json(MockHttpServletRequestBuilder request, String body) {
        return request.contentType(MediaType.APPLICATION_JSON).content(body);
    }
}
