package com.example.udemy.limit_service_microservices.bean;

/* The response body: {"minimum":5,"maximum":995}. A record replaces the old class full of getters and setters. */
public record Limits(int minimum, int maximum) {
}
