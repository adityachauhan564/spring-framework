package com.springcore.topic01_why_spring;

/*
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic01_why_spring.ManualWiringDemo
 * Key idea : without Spring, YOUR code creates every object and connects them.
 *            Fine for 2 classes - painful for 200: every change of implementation
 *            means editing and recompiling this "wiring" code.
 * Next     : ContainerWiringDemo does the same wiring from a config file.
 */
public class ManualWiringDemo {

    public static void main(String[] args) {
        PaymentService payment = new UpiPayment();              // 1. create the dependency
        OrderService orderService = new OrderService(payment);  // 2. hand it over (dependency injection by hand)

        System.out.println(orderService.placeOrder(1000));
        System.out.println("To switch to CardPayment you must edit and recompile this class.");
    }
}
