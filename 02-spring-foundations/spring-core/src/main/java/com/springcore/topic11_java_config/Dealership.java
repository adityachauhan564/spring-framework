package com.springcore.topic11_java_config;

/* Built in DealershipConfig. AppConfig brings that config in with @Import. */
public class Dealership {

    private final Car car;

    public Dealership(Car car) {
        this.car = car;
    }

    public String sell() {
        return "Selling a car whose " + car.drive();
    }
}
