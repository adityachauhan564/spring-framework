package com.learning.springboot.basics.topic05_actuator;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import com.learning.springboot.basics.topic02_configuration_properties.ShopProperties;

/*
 * Topic    : Actuator - production-ready endpoints for free
 * Key idea : - The actuator starter adds ready-made URLs like /actuator/health, /info, /metrics...
 *              The support team and tools (load balancers, Kubernetes, monitoring) call them
 *              to check whether the app is fine.
 *            - Like a doctor's check-up report for your app: pulse, BP, temperature.
 *            - A HealthIndicator bean adds your OWN check to /actuator/health.
 *            - Only endpoints listed in management.endpoints.web.exposure.include can be
 *              opened over HTTP. Never make env or heapdump public - they leak secrets.
 * Try this : Open http://localhost:8080/actuator/health , then set discount-percent to 80 and open it again.
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
