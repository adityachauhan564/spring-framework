package com.springcore.topic01_why_spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic01_why_spring.ContainerWiringDemo
 * Key idea : IoC (Inversion of Control) - the Spring container, not your code, creates the
 *            objects ("beans") listed in why-spring.xml and injects their dependencies (DI).
 *            Switching UPI -> card is now a one-word change in XML; no Java code changes.
 * Try this : change ref="upiPayment" to ref="cardPayment" in why-spring.xml and run again.
 */
public class ContainerWiringDemo {

    public static void main(String[] args) {
        // try-with-resources closes the container (and its beans) at the end
        try (var context = new ClassPathXmlApplicationContext("com/springcore/topic01_why_spring/why-spring.xml")) {
            OrderService orderService = context.getBean(OrderService.class);   // ask, don't create
            System.out.println(orderService.placeOrder(1000));

            System.out.println("Beans the container created: " + String.join(", ", context.getBeanDefinitionNames()));
        }
    }
}
