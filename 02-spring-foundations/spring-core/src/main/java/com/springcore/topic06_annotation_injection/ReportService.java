package com.springcore.topic06_annotation_injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*
 * 3. FIELD injection and SETTER injection with @Autowired. Both work, but:
 *    - Field injection: the field cannot be final, and in a plain unit test (without Spring)
 *      you have no way to set it, except reflection. So prefer the constructor.
 *    - Setter injection is good for OPTIONAL dependencies. required = false means:
 *      "inject it if such a bean exists, otherwise just leave it null".
 *      Like a spare tyre - nice to have, but the car still runs without it.
 */
public class ReportService {

    @Autowired
    @Qualifier("emailSender")
    private MessageSender sender;                 // field injection (avoid this in new code)

    private MessageSender backupSender;

    @Autowired(required = false)
    public void setBackupSender(@Qualifier("smsSender") MessageSender backupSender) {
        this.backupSender = backupSender;         // setter injection, for a dependency that is optional
    }

    public String report(String text) {
        return sender.send("REPORT " + text) + (backupSender != null ? " | backup: " + backupSender.send(text) : "");
    }
}
