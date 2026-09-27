package com.springcore.topic06_annotation_injection;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Annotation-driven injection
 * Key idea : the beans are still declared in XML, but <context:annotation-config/> tells
 *            Spring to read @Autowired / @Qualifier inside them. No ref="..." needed.
 *            Ambiguity rules: @Qualifier("name") wins, then primary="true" / @Primary,
 *            otherwise the context fails with NoUniqueBeanDefinitionException.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic06_annotation_injection.AnnotationInjectionDemo
 * Try this : remove primary="true" from the XML - which bean now fails to start, and why?
 */
public class AnnotationInjectionDemo {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("com/springcore/topic06_annotation_injection/annotation-injection.xml")) {
            System.out.println("constructor + @Primary:   " + context.getBean(NotificationService.class).notifyUser("Welcome!"));
            System.out.println("constructor + @Qualifier: " + context.getBean(AlertService.class).alert("Disk full"));
            System.out.println("field + setter:           " + context.getBean(ReportService.class).report("Monthly sales"));
        }
    }
}
