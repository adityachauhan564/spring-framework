package com.springcore.topic06_annotation_injection;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Annotation-driven injection
 * Key idea : The beans are still listed in XML, but the wiring is done by annotations.
 *            - <context:annotation-config/> tells Spring to read @Autowired / @Qualifier
 *              inside the classes. So no ref="..." is needed in the XML.
 *            - When two beans match, Spring decides in this order:
 *                1. @Qualifier("name") wins,
 *                2. then the bean marked primary="true" / @Primary,
 *                3. otherwise startup fails with NoUniqueBeanDefinitionException.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic06_annotation_injection.AnnotationInjectionDemo
 * Try this : Remove primary="true" from the XML. Which bean now fails to start, and why?
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
