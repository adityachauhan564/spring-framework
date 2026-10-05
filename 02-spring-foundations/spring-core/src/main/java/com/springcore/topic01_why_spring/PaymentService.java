package com.springcore.topic01_why_spring;

/*
 * Topic    : Why Spring? - the problem it solves
 * Read     : PaymentService -> UpiPayment / CardPayment -> OrderService
 *            -> ManualWiringDemo -> ContainerWiringDemo
 * This is the same idea as 01-core-java topic 14 (interfaces and dependency injection).
 * The only difference: now Spring does the wiring for us.
 */
public interface PaymentService {

    String pay(double amount);
}
