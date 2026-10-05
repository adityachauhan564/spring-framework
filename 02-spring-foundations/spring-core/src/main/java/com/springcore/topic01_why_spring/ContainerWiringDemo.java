package com.springcore.topic01_why_spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic01_why_spring.ContainerWiringDemo
 * Key idea : IoC (Inversion of Control) means control is turned around:
 *            - Earlier, YOUR code created every object with 'new'.
 *            - Now the Spring container (a box that creates and holds objects) does it for you.
 *            - It reads why-spring.xml, creates the objects listed there ("beans"),
 *              and hands each one the objects it needs. That handing-over is DI (Dependency Injection).
 *            - Like a caterer at a wedding: you give the menu, they arrange everything.
 *            - Switching UPI -> card is now a one-word change in XML. No Java code changes.
 * Try this : Change ref="upiPayment" to ref="cardPayment" in why-spring.xml and run again.
 */
public class ContainerWiringDemo {

    public static void main(String[] args) {
        // try-with-resources closes the container (and all its beans) automatically at the end
        try (var context = new ClassPathXmlApplicationContext("com/springcore/topic01_why_spring/why-spring.xml")) {
            OrderService orderService = context.getBean(OrderService.class);   // we ASK Spring for the object, we don't create it
            System.out.println(orderService.placeOrder(1000));

            System.out.println("Beans the container created: " + String.join(", ", context.getBeanDefinitionNames()));
        }
    }
}
