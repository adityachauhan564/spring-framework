package com.springcore.topic03_constructor_injection;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Constructor injection with XML
 * Key idea : <constructor-arg> values are passed to the constructor. Match them by
 *            position (default), index, parameter name or type - or use the c: namespace.
 *            Constructor injection = required dependencies, immutable objects.
 *            Setter injection (topic02) = optional dependencies that may change.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic03_constructor_injection.ConstructorInjectionDemo
 * Try this : remove one <constructor-arg> from person1 and read the error.
 */
public class ConstructorInjectionDemo {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("com/springcore/topic03_constructor_injection/constructor-injection.xml")) {
            for (String id : new String[] {"byPosition", "byIndex", "byName", "byType", "cNamespace"}) {
                System.out.printf("%-11s -> %s%n", id, context.getBean(id, Person.class));
            }
        }
    }
}
