package com.springcore.topic01_why_spring;

/*
 * Business logic that depends only on the PaymentService interface.
 * It never calls 'new UpiPayment()': someone else hands it a PaymentService
 * through the constructor. That "someone else" is you (ManualWiringDemo)
 * or the Spring container (ContainerWiringDemo).
 */
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String placeOrder(double amount) {
        return paymentService.pay(amount) + " - order placed";
    }
}
