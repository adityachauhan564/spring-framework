package com.springcore.topic06_annotation_injection;

public class SmsSender implements MessageSender {

    @Override
    public String send(String message) {
        return "SMS: " + message;
    }
}
