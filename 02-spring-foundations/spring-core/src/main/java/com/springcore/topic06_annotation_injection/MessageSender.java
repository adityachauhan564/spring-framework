package com.springcore.topic06_annotation_injection;

/*
 * Topic    : @Autowired, @Qualifier and @Primary
 * Read     : MessageSender -> EmailSender / SmsSender -> NotificationService,
 *            AlertService, ReportService -> annotation-injection.xml -> AnnotationInjectionDemo
 * Two implementations of one interface = the ambiguity topic05 could not solve.
 */
public interface MessageSender {

    String send(String message);
}
