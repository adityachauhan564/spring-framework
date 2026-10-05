package com.springcore.topic13_aop;

import org.springframework.stereotype.Service;

/*
 * Only business logic here: no logging code, no timing code. The aspects add those from outside.
 */
@Service
public class OrderService {

    @Timed
    public String placeOrder(String item) {
        return "order placed for " + item;
    }

    public void cancelOrder(int orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("no order with id " + orderId);
        }
    }

    // Self-invocation trap: this.placeOrder(...) calls the REAL object directly, not the proxy,
    // so no aspect runs for that inner call. The same problem happens with @Transactional.
    public String placeTwoOrders() {
        return placeOrder("book") + " / " + this.placeOrder("pen");
    }
}
