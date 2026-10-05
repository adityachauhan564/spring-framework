package com.springcore.topic06_annotation_injection;

/*
 * Topic    : @Autowired, @Qualifier and @Primary
 * Read     : MessageSender -> EmailSender / SmsSender -> NotificationService,
 *            AlertService, ReportService -> annotation-injection.xml -> AnnotationInjectionDemo
 * One interface, two implementations (email and SMS). This is exactly the
 * "two beans of the same type" problem that topic05 could not solve.
 */
public interface MessageSender {

    String send(String message);
}
