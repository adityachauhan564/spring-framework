package com.example.udemy.limit_service_microservices.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * Binds every "limits-service.*" key. The values come, in order of precedence, from:
 *   1. the config server (git-local-config-repo/limit-service-microservices[-profile].properties)
 *   2. this service's own application.properties (the fallback when the server is down)
 *
 * Renamed from "Configuration": that name clashed with Spring's @Configuration annotation.
 * Kept as a class with SETTERS (not a record) on purpose: after POST /actuator/refresh, Spring
 * Cloud re-binds @ConfigurationProperties beans in place, which needs setters.
 * (Fields injected with @Value are NOT updated by a refresh unless their bean is @RefreshScope.)
 */
@Component
@ConfigurationProperties("limits-service")
public class LimitsProperties {

    private int minimum;
    private int maximum;

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
