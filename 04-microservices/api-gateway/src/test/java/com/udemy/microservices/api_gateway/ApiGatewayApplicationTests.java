package com.udemy.microservices.api_gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.test.context.ActiveProfiles;

/*
 * Checks the route table itself - which path goes where - without starting any backend.
 * (The routes are exercised for real by the start-all walkthrough in the stage README.)
 */
@SpringBootTest
@ActiveProfiles("test")
class ApiGatewayApplicationTests {

    @Autowired
    RouteLocator routeLocator;

    @Test
    void everyServiceHasALoadBalancedRoute() {
        Map<String, String> uriByRouteId = routeLocator.getRoutes()
                .collect(Collectors.toMap(Route::getId, route -> route.getUri().toString()))
                .block();

        assertEquals("lb://currency-exchange", uriByRouteId.get("currency-exchange"));
        assertEquals("lb://currency-conversion-service", uriByRouteId.get("currency-conversion"));
        assertEquals("lb://currency-conversion-service", uriByRouteId.get("currency-conversion-feign"));
        assertEquals("lb://currency-conversion-service", uriByRouteId.get("currency-conversion-new"));
        assertEquals("http://httpbin.org:80", uriByRouteId.get("httpbin-demo"));
    }
}
