package com.springcore.topic07_component_scanning;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Component scanning and stereotypes
 * Key idea : Component scanning = Spring searches a package for classes marked @Component,
 *            and makes a bean of each one. You don't list them yourself any more.
 *            Like a teacher taking attendance: whoever has raised a hand (@Component) is counted.
 *            Two ways to switch scanning on - both give the same beans:
 *              XML:  <context:component-scan base-package="..."/>
 *              Java: new AnnotationConfigApplicationContext("base.package")   (used from topic08 on)
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic07_component_scanning.ComponentScanningDemo
 * Try this : Remove @Component from Teacher and read the error from ClassroomService.
 */
public class ComponentScanningDemo {

    public static void main(String[] args) {
        try (var xmlContext = new ClassPathXmlApplicationContext("com/springcore/topic07_component_scanning/component-scan.xml")) {
            System.out.println("XML component-scan found: " + myBeans(xmlContext.getBeanDefinitionNames()));
            System.out.println(xmlContext.getBean(ClassroomService.class).describe());
        }

        try (var javaContext = new AnnotationConfigApplicationContext("com.springcore.topic07_component_scanning")) {
            System.out.println("\nJava package scan found:  " + myBeans(javaContext.getBeanDefinitionNames()));
            System.out.println("getBean(\"headTeacher\"):   " + javaContext.getBean("headTeacher"));
        }
    }

    // hides Spring's own internal beans (their names have dots), so only our beans are listed
    private static String myBeans(String[] names) {
        return Arrays.stream(names).filter(name -> !name.contains(".")).sorted().toList().toString();
    }
}
