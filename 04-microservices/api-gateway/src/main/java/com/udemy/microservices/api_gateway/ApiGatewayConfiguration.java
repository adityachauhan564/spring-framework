package com.udemy.microservices.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Topic    : The API gateway - one front door for every microservice
 * Key idea : Clients call ONLY the gateway (port 8765). Like the main gate of a housing society:
 *            every visitor enters through it, and the guard sends them to the right building.
 *            Each route says:
 *              which requests   - a predicate (a condition), here the path
 *              what to change   - filters (add a header, rewrite the path...)
 *              where to send it - a URI. lb://NAME = "ask Eureka for NAME, and pick one instance"
 *            So clients never know how many instances exist or where they run, and
 *            common work (logging, login checks, rate limits) happens in one place.
 * Try this : curl localhost:8765/currency-exchange/from/USD/to/INR
 *            curl localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10   (rewritten)
 */
@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                // external demo (needs internet): shows request filters at work. httpbin sends
                // back the header and parameter that the gateway added
                .route("httpbin-demo", p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route("currency-exchange", p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route("currency-conversion", p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion-service"))
                .route("currency-conversion-feign", p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion-service"))
                // a public path that is different from the backend path: rename it without touching the service
                .route("currency-conversion-new", p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion-service"))
                .build();
    }
}
