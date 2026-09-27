package com.springcore.topic13_aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * Topic    : AOP with @Aspect
 * Key idea : the bean you get is a PROXY around OrderService. Calls go through the proxy,
 *            which runs the matching advice around the real method. Spring's @Transactional,
 *            @Cacheable and @Async all work this way - which is why they don't apply when a
 *            method calls another method of the same object (self-invocation).
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic13_aop.AopDemo
 * Try this : write an aspect that prints a warning whenever cancelOrder is called.
 */
public class AopDemo {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(AopConfig.class)) {
            OrderService orders = context.getBean(OrderService.class);
            System.out.println("The bean's real class: " + orders.getClass().getSimpleName() + "  (a proxy, not OrderService)");

            System.out.println("\n1. a normal call:");
            orders.placeOrder("laptop");

            System.out.println("\n2. a call that throws:");
            try {
                orders.cancelOrder(-1);
            } catch (IllegalArgumentException e) {
                System.out.println("  caller caught: " + e.getMessage());
            }

            System.out.println("\n3. self-invocation - watch the inner placeOrder calls get no [log]/[timing]:");
            orders.placeTwoOrders();
        }
    }
}
