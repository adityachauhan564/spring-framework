package com.springcore.topic02_xml_setter_injection;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Setter injection with XML
 * Key idea : Setter injection = Spring fills the object's fields by calling its setters.
 *            - First Spring calls the no-arg constructor (creates an empty object).
 *            - Then it calls one setter for every <property> in the XML.
 *            - name="studentName" in the XML means Spring calls setStudentName(...).
 *            - Like filling an admission form: first a blank form, then one box at a time.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic02_xml_setter_injection.SetterInjectionDemo
 * Try this : Misspell a property name in the XML and read the error Spring gives you.
 */
public class SetterInjectionDemo {

    public static void main(String[] args) {
        System.out.println("Creating the container (beans are built now, not at getBean):");
        try (var context = new ClassPathXmlApplicationContext("com/springcore/topic02_xml_setter_injection/setter-injection.xml")) {

            Student student1 = context.getBean("student1", Student.class);   // passing Student.class means we don't need a cast
            Student student2 = context.getBean("student2", Student.class);
            Student student3 = context.getBean("student3", Student.class);

            System.out.println("\n<value> element:     " + student1);
            System.out.println("value attribute:     " + student2);
            System.out.println("p: namespace:        " + student3);
        }
    }
}
