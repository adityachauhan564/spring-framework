package com.springcore.topic01_why_spring;

public class UpiPayment implements PaymentService {

    @Override
    public String pay(double amount) {
        return "Paid " + amount + " using UPI";
    }
}
