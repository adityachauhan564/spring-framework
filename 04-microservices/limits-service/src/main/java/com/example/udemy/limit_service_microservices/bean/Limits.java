package com.example.udemy.limit_service_microservices.bean;

/* The response body: {"minimum":5,"maximum":995}. A record replaces the old getter/setter class. */
public record Limits(int minimum, int maximum) {
}
