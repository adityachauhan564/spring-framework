package com.springcore.topic01_why_spring;

/*
 * Topic    : Why Spring? - the problem it solves
 * Read     : PaymentService -> UpiPayment / CardPayment -> OrderService
 *            -> ManualWiringDemo -> ContainerWiringDemo
 * Same idea as 01-core-java head-first-java topic05, now handed to Spring.
 */
public interface PaymentService {

    String pay(double amount);
}
