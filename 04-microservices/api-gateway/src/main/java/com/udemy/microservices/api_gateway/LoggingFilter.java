package com.udemy.microservices.api_gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/*
 * A GlobalFilter runs for EVERY request on every route (a route filter runs only on its own route).
 * The gateway is REACTIVE (Spring WebFlux): filters return Mono<Void> - "work that will finish
 * later" - instead of keeping a thread waiting while the backend answers.
 * Like a token system at a hospital: you get a token and sit down, nobody stands blocking the queue.
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
