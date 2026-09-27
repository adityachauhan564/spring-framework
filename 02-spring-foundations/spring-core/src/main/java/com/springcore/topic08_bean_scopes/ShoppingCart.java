package com.springcore.topic08_bean_scopes;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/* Prototype: a NEW object every time the container is asked for one. */
@Component
@Scope("prototype")
public class ShoppingCart {

    private static final AtomicInteger COUNTER = new AtomicInteger();
    private final int id = COUNTER.incrementAndGet();

    public int getId() {
        return id;
    }
}
