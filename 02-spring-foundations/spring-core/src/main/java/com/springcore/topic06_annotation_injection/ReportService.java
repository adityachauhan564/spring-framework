package com.springcore.topic06_annotation_injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*
 * 3. FIELD injection and SETTER injection with @Autowired - both work, but:
 *    - a field can't be final, and the class can't be created in a plain unit test
 *      without Spring (or reflection) - prefer the constructor;
 *    - setter injection suits OPTIONAL dependencies: required = false means
 *      "inject it if such a bean exists, otherwise leave it null".
 */
public class ReportService {

    @Autowired
    @Qualifier("emailSender")
    private MessageSender sender;                 // field injection (avoid in new code)

    private MessageSender backupSender;

    @Autowired(required = false)
    public void setBackupSender(@Qualifier("smsSender") MessageSender backupSender) {
        this.backupSender = backupSender;         // setter injection for an optional dependency
    }

    public String report(String text) {
        return sender.send("REPORT " + text) + (backupSender != null ? " | backup: " + backupSender.send(text) : "");
    }
}
