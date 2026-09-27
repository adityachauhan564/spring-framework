package com.springcore.topic01_why_spring;

public class CardPayment implements PaymentService {

    @Override
    public String pay(double amount) {
        return "Paid " + amount + " using a card";
    }
}
