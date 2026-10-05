package com.springcore.topic08_bean_scopes;

import org.springframework.stereotype.Component;

/*
 * Singleton (the default scope): Spring makes only ONE object for this bean in the container,
 * and gives that same object to everyone who asks for it.
 * (One per bean, not one per class: two @Bean methods returning the same class give two objects.)
 * Like the one notice board in a college - every student reads the same board.
 */
@Component
public class AppSettings {

    public AppSettings() {
        System.out.println("  AppSettings created (singleton - once, at startup)");
    }
}
