package com.udemy.microservices.naming_server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

/*
 * Starts the real Eureka server (the naming server - a phone directory where every service
 * writes its address) on a random port, and talks to it over HTTP the same way the microservices do.
 * (Eureka's REST API is not a Spring MVC controller, so MockMvc cannot reach it.)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NamingServerApplicationTests {

    @LocalServerPort
    int port;

    private final HttpClient http = HttpClient.newHttpClient();

    private HttpResponse<String> get(String path) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:" + port + path))
                .header("Accept", "application/json").build();
        return http.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Test
    void registryApiAnswersAndStartsEmpty() throws Exception {
        HttpResponse<String> apps = get("/eureka/apps");
        assertEquals(200, apps.statusCode());
        // register-with-eureka=false: the server does not write its own name in the directory
        assertTrue(!apps.body().toUpperCase().contains("NAMING-SERVER"));
    }

    @Test
    void healthIsUp() throws Exception {
        assertTrue(get("/actuator/health").body().contains("UP"));
    }
}
