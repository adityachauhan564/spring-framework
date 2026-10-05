package com.springcore.topic01_why_spring;

/*
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic01_why_spring.ManualWiringDemo
 * Key idea : Without Spring, YOUR code creates every object and connects them together.
 *            - For 2 classes this is fine.
 *            - For 200 classes it becomes painful: every time you change an implementation,
 *              you must edit and recompile this "wiring" code.
 *            - Like cooking for 2 people at home vs cooking for a 500-guest wedding yourself.
 * Next     : ContainerWiringDemo does the same wiring, but from a config file.
 */
public class ManualWiringDemo {

    public static void main(String[] args) {
        PaymentService payment = new UpiPayment();              // 1. create the object that OrderService needs
        OrderService orderService = new OrderService(payment);  // 2. hand it over (this is dependency injection, done by hand)

        System.out.println(orderService.placeOrder(1000));
        System.out.println("To switch to CardPayment you must edit and recompile this class.");
    }
}
