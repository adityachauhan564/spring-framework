package com.springcore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springcore.topic01_why_spring.OrderService;
import com.springcore.topic03_constructor_injection.Person;
import com.springcore.topic06_annotation_injection.AlertService;
import com.springcore.topic06_annotation_injection.NotificationService;

/* Checks the XML-configured topics (01-06) wire what their demos claim. */
class XmlConfigurationTest {

    private static ClassPathXmlApplicationContext load(String path) {
        return new ClassPathXmlApplicationContext("com/springcore/" + path);
    }

    @Test
    void containerInjectsTheConfiguredPayment() {
        try (var context = load("topic01_why_spring/why-spring.xml")) {
            assertTrue(context.getBean(OrderService.class).placeOrder(10).contains("UPI"));
        }
    }

    @Test
    void everyConstructorArgStyleBuildsTheSamePerson() {
        try (var context = load("topic03_constructor_injection/constructor-injection.xml")) {
            assertEquals("Person[name=Khushi, personId=18]", context.getBean("byName", Person.class).toString());
            assertEquals("Person[name=Aditya, personId=17]", context.getBean("byIndex", Person.class).toString());
        }
    }

    @Test
    void byTypeAutowiringFailsWithTwoCandidates() {
        assertThrows(UnsatisfiedDependencyException.class,
                () -> load("topic05_xml_autowiring/autowiring-ambiguous.xml").close());
    }

    @Test
    void primaryIsTheDefaultAndQualifierOverridesIt() {
        try (var context = load("topic06_annotation_injection/annotation-injection.xml")) {
            assertTrue(context.getBean(NotificationService.class).notifyUser("x").startsWith("EMAIL"));
            assertTrue(context.getBean(AlertService.class).alert("x").startsWith("SMS"));
        }
    }
}
