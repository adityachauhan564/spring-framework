package com.learning.springboot.basics.topic05_actuator;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import com.learning.springboot.basics.topic02_configuration_properties.ShopProperties;

/*
 * Topic    : Actuator - production-ready endpoints for free
 * Key idea : the actuator starter adds /actuator/health, /info, /metrics... that operators and
 *            tools (load balancers, Kubernetes, monitoring) call to check the app.
 *            A HealthIndicator bean adds your OWN check to /actuator/health.
 *            Only endpoints listed in management.endpoints.web.exposure.include are reachable
 *            over HTTP - never expose env or heapdump publicly, they leak secrets.
 * Try this : open http://localhost:8080/actuator/health , then set discount-percent to 80 and reopen.
 */
@Component("shop")
public class ShopHealthIndicator implements HealthIndicator {

    private final ShopProperties shop;

    public ShopHealthIndicator(ShopProperties shop) {
        this.shop = shop;
    }

    @Override
    public Health health() {
        if (shop.discountPercent() > 50) {
            return Health.down().withDetail("reason", "discount above 50% looks like a configuration mistake").build();
        }
        return Health.up().withDetail("currency", shop.currency()).build();
    }
}
