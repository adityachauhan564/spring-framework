package com.example.udemy.microservices.currency_exchange_service;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

/*
 * Topic    : Fault tolerance with Resilience4j
 * Key idea : in a microservice system, the services you call WILL sometimes be slow or down.
 *            Without protection, one failing service drags every caller down with it.
 *   @Retry          - try again a few times (transient network blips), then use the fallback
 *   @CircuitBreaker - after many failures, STOP calling for a while ("open" circuit) and answer
 *                     with the fallback at once, giving the broken service time to recover
 *   @RateLimiter    - allow at most N calls per period, reject the rest
 *   The fallbackMethod has the same signature plus a Throwable, and returns a safe default.
 *   Every setting (attempts, thresholds, limits) lives in application.properties.
 * Try this : curl localhost:8000/sample-api           (retries, then the fallback - watch the log)
 *            for i in $(seq 1 20); do curl -s localhost:8000/sample-api/circuit-breaker; echo; done
 *            curl localhost:8000/actuator/circuitbreakers   (state: CLOSED -> OPEN)
 *            for i in $(seq 1 5); do curl -s localhost:8000/sample-api/rate-limited; echo; done
 */
@RestController
public class CircuitBreakerController {

    private static final Logger log = LoggerFactory.getLogger(CircuitBreakerController.class);

    // a URL where nothing is listening, to simulate a dependency that is down
    private static final String BROKEN_SERVICE = "http://localhost:8080/some-dummy-url";

    private final RestClient restClient;
    private final AtomicInteger attempts = new AtomicInteger();

    public CircuitBreakerController(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "fallback")
    public String retryDemo() {
        log.info("sample-api call attempt {}", attempts.incrementAndGet());
        return callBrokenService();
    }

    @GetMapping("/sample-api/circuit-breaker")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public String circuitBreakerDemo() {
        log.info("circuit breaker let the call through");
        return callBrokenService();
    }

    @GetMapping("/sample-api/rate-limited")
    @RateLimiter(name = "default", fallbackMethod = "rateLimited")
    public String rateLimiterDemo() {
        return "allowed";
    }

    private String callBrokenService() {
        return restClient.get().uri(BROKEN_SERVICE).retrieve().body(String.class);
    }

    public String fallback(Exception e) {
        return "fallback-response (" + e.getClass().getSimpleName() + ")";
    }

    public String rateLimited(Exception e) {
        return "too many requests - try again later";
    }
}
