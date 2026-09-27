package com.udemy.microservices.api_gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/*
 * A GlobalFilter runs for EVERY request on every route (a route filter runs only on its route).
 * The gateway is REACTIVE (Spring WebFlux): filters return Mono<Void> - "work that completes
 * later" - instead of blocking a thread while the backend answers.
 */
@Component
public class LoggingFilter implements GlobalFilter {

    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Request received -> {} {}", exchange.getRequest().getMethod(), exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
