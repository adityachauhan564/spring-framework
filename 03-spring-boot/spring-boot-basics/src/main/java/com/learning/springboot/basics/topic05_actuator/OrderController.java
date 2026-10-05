package com.learning.springboot.basics.topic05_actuator;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/*
 * A custom METRIC: a number that counts how often something happens.
 * Like the counter on a toll plaza that counts every car going through.
 * Actuator uses Micrometer (a metrics library). A Counter registered on the MeterRegistry shows up at
 * /actuator/metrics/shop.orders.placed - and could be sent to Prometheus/Grafana without any change.
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
