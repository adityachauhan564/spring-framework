package com.springcore.topic06_annotation_injection;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * 1. CONSTRUCTOR injection - the recommended style.
 *    There are two MessageSender beans. Spring picks the one marked primary="true"
 *    (when beans are made with annotations, the same thing is written as @Primary).
 *    If a class has only one constructor, @Autowired is optional.
 *    It is written here only to make it clear.
 */
public class NotificationService {

    private final MessageSender sender;

    @Autowired
    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public String notifyUser(String text) {
        return sender.send(text);
    }
}
