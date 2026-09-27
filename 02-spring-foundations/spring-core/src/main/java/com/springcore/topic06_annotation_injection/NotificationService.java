package com.springcore.topic06_annotation_injection;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * 1. CONSTRUCTOR injection - the recommended style.
 *    Two MessageSender beans exist; Spring picks the one marked primary="true"
 *    (@Primary when the bean is declared with annotations).
 *    With a single constructor, @Autowired is optional - it's written here to be explicit.
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
