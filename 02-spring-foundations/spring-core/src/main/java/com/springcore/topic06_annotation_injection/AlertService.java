package com.springcore.topic06_annotation_injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*
 * 2. @Qualifier overrides @Primary: "I want exactly the bean named smsSender".
 */
public class AlertService {

    private final MessageSender sender;

    @Autowired
    public AlertService(@Qualifier("smsSender") MessageSender sender) {
        this.sender = sender;
    }

    public String alert(String text) {
        return sender.send("ALERT " + text);
    }
}
