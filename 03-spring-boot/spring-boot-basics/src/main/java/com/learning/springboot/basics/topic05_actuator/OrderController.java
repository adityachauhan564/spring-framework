package com.learning.springboot.basics.topic05_actuator;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/*
 * A custom METRIC: count how often something happens.
 * Actuator uses Micrometer; a Counter registered on the MeterRegistry shows up at
 * /actuator/metrics/shop.orders.placed - and could be sent to Prometheus/Grafana unchanged.
 * Try this : curl -X POST http://localhost:8080/orders   (a few times)
 *            curl http://localhost:8080/actuator/metrics/shop.orders.placed
 */
@RestController
public class OrderController {

    private final Counter ordersPlaced;

    public OrderController(MeterRegistry registry) {
        this.ordersPlaced = Counter.builder("shop.orders.placed").description("orders placed since startup").register(registry);
    }

    @PostMapping("/orders")
    public Map<String, Object> placeOrder() {
        ordersPlaced.increment();
        return Map.of("status", "placed", "totalSinceStartup", (long) ordersPlaced.count());
    }
}
