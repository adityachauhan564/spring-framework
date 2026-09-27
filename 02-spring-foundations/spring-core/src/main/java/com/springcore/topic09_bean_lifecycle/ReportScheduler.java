package com.springcore.topic09_bean_lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/*
 * Way 3: @PostConstruct / @PreDestroy (jakarta.annotation) - the recommended way.
 * Needs annotation processing: <context:annotation-config/>, component scanning,
 * or an AnnotationConfigApplicationContext.
 */
public class ReportScheduler {

    private String subject;

    public ReportScheduler() {
        System.out.println("  [ReportScheduler] 1. constructor");
    }

    public void setSubject(String subject) {
        System.out.println("  [ReportScheduler] 2. setter: subject=" + subject);
        this.subject = subject;
    }

    @PostConstruct
    public void start() {
        System.out.println("  [ReportScheduler] 3. @PostConstruct: scheduling '" + subject + "' reports");
    }

    @PreDestroy
    public void stop() {
        System.out.println("  [ReportScheduler] @PreDestroy: cancelling reports");
    }
}
