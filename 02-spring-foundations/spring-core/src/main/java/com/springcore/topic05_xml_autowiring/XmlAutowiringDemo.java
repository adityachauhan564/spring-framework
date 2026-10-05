package com.springcore.topic05_xml_autowiring;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Autowiring in XML
 * Key idea : Autowiring = instead of writing ref="..." yourself, Spring finds the dependency for you.
 *              byName      - finds a bean whose id is the same as the property name (address -> setAddress)
 *              byType      - finds the ONE bean whose type matches the setter parameter
 *              constructor - same as byType, but passes it through the constructor
 *            - byType fails when two beans have the same type. Spring cannot choose.
 *              The second XML file shows this on purpose.
 *            - Like a courier: byName = deliver by flat number, byType = "give it to whoever is the security guard".
 *              If there are two security guards, the courier gets confused.
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
            // the real reason inside is a NoUniqueBeanDefinitionException ("more than one bean matched")
            System.out.println("  failed: " + e.getMostSpecificCause().getMessage());
        }
    }
}
