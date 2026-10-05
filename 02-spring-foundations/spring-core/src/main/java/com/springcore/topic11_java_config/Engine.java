package com.springcore.topic11_java_config;

/* A plain class with no annotations. AppConfig turns it into a bean using @Bean. */
public class Engine {

    public String start() {
        return "engine started";
    }
}
