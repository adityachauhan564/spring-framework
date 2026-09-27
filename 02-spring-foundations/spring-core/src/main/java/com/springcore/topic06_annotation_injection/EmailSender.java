package com.springcore.topic06_annotation_injection;

public class EmailSender implements MessageSender {

    @Override
    public String send(String message) {
        return "EMAIL: " + message;
    }
}
