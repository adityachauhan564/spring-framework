package com.springcore.topic09_bean_lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Bean lifecycle callbacks
 * Key idea : for every bean: 1. constructor -> 2. dependencies/setters -> 3. init callback
 *            -> bean is used -> destroy callback when the CONTAINER CLOSES.
 *            Three ways to hook in (see the three classes). Destroy callbacks never run for
 *            prototype beans, and never run if the container is not closed.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic09_bean_lifecycle.BeanLifecycleDemo
 * Try this : replace try-with-resources with a plain variable - the destroy lines disappear.
 *            (context.registerShutdownHook() is the other way to close on JVM exit.)
 */
public class BeanLifecycleDemo {

    public static void main(String[] args) {
        System.out.println("Starting the container:");
        try (var context = new ClassPathXmlApplicationContext("com/springcore/topic09_bean_lifecycle/lifecycle.xml")) {
            System.out.println("\nAll beans ready - the application runs here.\n");
            System.out.println("Closing the container:");
        }   // close() runs the destroy callbacks, in reverse order of creation
    }
}
