package com.springcore.topic05_xml_autowiring;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Autowiring in XML
 * Key idea : instead of writing ref="..." yourself, let Spring find the dependency:
 *              byName      - a bean whose id matches the property name (address -> setAddress)
 *              byType      - the ONE bean whose type matches the setter parameter
 *              constructor - byType, but through the constructor
 *            byType fails when two beans have the same type - the second XML shows it.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic05_xml_autowiring.XmlAutowiringDemo
 * Next     : topic06 does the same with @Autowired and fixes the ambiguity with @Qualifier.
 */
public class XmlAutowiringDemo {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("com/springcore/topic05_xml_autowiring/autowiring.xml")) {
            System.out.println("byName:      " + context.getBean("empByName"));
            System.out.println("byType:      " + context.getBean("empByType"));
            System.out.println("constructor: " + context.getBean("empByConstructor"));
        }

        System.out.println("\nTwo Address beans + autowire=\"byType\":");
        try (var broken = new ClassPathXmlApplicationContext("com/springcore/topic05_xml_autowiring/autowiring-ambiguous.xml")) {
            System.out.println("unexpected: context started");
        } catch (BeanCreationException e) {
            // the root cause is a NoUniqueBeanDefinitionException
            System.out.println("  failed: " + e.getMostSpecificCause().getMessage());
        }
    }
}
