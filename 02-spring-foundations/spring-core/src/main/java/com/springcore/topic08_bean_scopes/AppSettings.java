package com.springcore.topic08_bean_scopes;

import org.springframework.stereotype.Component;

/* Singleton (the default scope): ONE object per container, shared by everyone who asks. */
@Component
public class AppSettings {

    public AppSettings() {
        System.out.println("  AppSettings created (singleton - once, at startup)");
    }
}
