package com.springcore.topic11_java_config;

/* Needs an Engine - wired in AppConfig.car(), not with annotations in this class. */
public class Car {

    private final Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    public String drive() {
        return engine.start() + ", car is moving";
    }
}
