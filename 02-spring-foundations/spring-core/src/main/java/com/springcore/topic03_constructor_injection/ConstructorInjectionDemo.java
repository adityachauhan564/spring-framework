package com.springcore.topic03_constructor_injection;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Constructor injection with XML
 * Key idea : Constructor injection = Spring passes the values straight into the constructor.
 *            - Each <constructor-arg> in the XML becomes one constructor argument.
 *            - Spring matches them by position (the default), by index, by parameter name,
 *              or by type. The c: namespace is a short way to write the same thing.
 *            - Use constructor injection for REQUIRED things. The object can never be half-built.
 *            - Use setter injection (topic02) for OPTIONAL things that may change later.
 *            - Like an Aadhaar card: name and number are printed when it is made, not filled in later.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic03_constructor_injection.ConstructorInjectionDemo
 * Try this : Remove one <constructor-arg> from person1 and read the error.
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
