package com.springcore.topic11_java_config;

/* A plain class - no annotations. AppConfig turns it into a bean with @Bean. */
public class Engine {

    public String start() {
        return "engine started";
    }
}
