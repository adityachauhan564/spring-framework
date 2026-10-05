package com.springcore.topic01_why_spring;

/*
 * The business logic. It only knows the PaymentService interface,
 * not whether the payment is UPI or card.
 * It never writes 'new UpiPayment()' itself. Someone else gives it a PaymentService
 * through the constructor. That "someone else" is either you (ManualWiringDemo)
 * or the Spring container (ContainerWiringDemo).
 * Like a shopkeeper who accepts any payment - the shop does not care which app you use.
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
