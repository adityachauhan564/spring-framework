package com.example.udemy.limit_service_microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.udemy.limit_service_microservices.bean.Limits;
import com.example.udemy.limit_service_microservices.configuration.LimitsProperties;

/*
 * Try this : curl localhost:8080/limits                     -> {"minimum":5,"maximum":995} (dev, from the config server)
 *            edit ../git-local-config-repo/limit-service-microservices-dev.properties, then
 *            curl -X POST localhost:8080/actuator/refresh   -> lists the keys that changed
 *            curl localhost:8080/limits                     -> the new values, no restart
 */
@RestController
public class LimitsController {

    private final LimitsProperties properties;

    public LimitsController(LimitsProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(properties.getMinimum(), properties.getMaximum());
    }
}
