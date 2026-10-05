package com.springcore.topic11_java_config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * Topic    : Java configuration - @Configuration, @Bean, @Import
 * Key idea : The whole container is described in Java code, with no XML at all.
 *            - Type-safe: a spelling mistake is caught by the compiler, not at runtime.
 *            - Refactor-friendly: rename a class in your IDE and the config updates too.
 *            - This is how Spring Boot apps are configured.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic11_java_config.JavaConfigDemo
 * Try this : Change @Configuration to @Component on AppConfig - the "same Engine" line becomes false.
 */
public class JavaConfigDemo {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            Car car = context.getBean(Car.class);
            Engine engine = context.getBean(Engine.class);

            System.out.println(car.drive());
            System.out.println("car's engine is the singleton Engine bean? " + (car.getEngine() == engine));
            System.out.println("AppConfig is really a proxy: " + context.getBean(AppConfig.class).getClass().getSimpleName());
            System.out.println("from the @Import-ed config: " + context.getBean(Dealership.class).sell());
        }
    }
}
