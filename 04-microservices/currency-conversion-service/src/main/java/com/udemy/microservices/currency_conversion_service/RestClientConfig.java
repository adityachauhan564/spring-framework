package com.udemy.microservices.currency_conversion_service;

import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/*
 * One RestClient for calls to currency-exchange, made "load-balanced" by the LoadBalancerInterceptor:
 * a URL like http://currency-exchange/... is looked up in Eureka (not DNS), and the calls are shared
 * across the instances - the same thing Feign does automatically.
 *
 * Built from Boot's own RestClient.Builder, so tracing still passes the trace id along.
 * Gotcha: do NOT declare a @LoadBalanced RestClient.Builder BEAN here. The Eureka client itself
 * uses the application's RestClient.Builder bean for its own HTTP calls. A load-balanced one makes
 * Eureka try to look up "localhost" as a service name, and then the service never registers.
 */
@Configuration
public class RestClientConfig {

    @Bean
    public RestClient currencyExchangeRestClient(RestClient.Builder builder, LoadBalancerInterceptor loadBalancer) {
        return builder.baseUrl("http://currency-exchange")
                .requestInterceptor(loadBalancer)
                .build();
    }
}
