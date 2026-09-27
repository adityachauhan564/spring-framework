package com.springcore.topic02_xml_setter_injection;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Topic    : Setter injection with XML
 * Key idea : Spring calls the no-arg constructor, then one setter per <property>.
 *            The name="studentName" attribute maps to setStudentName(...).
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic02_xml_setter_injection.SetterInjectionDemo
 * Try this : misspell a property name in the XML and read the error Spring gives you.
 */
public class SetterInjectionDemo {

    public static void main(String[] args) {
        System.out.println("Creating the container (beans are built now, not at getBean):");
        try (var context = new ClassPathXmlApplicationContext("com/springcore/topic02_xml_setter_injection/setter-injection.xml")) {

            Student student1 = context.getBean("student1", Student.class);   // the type argument avoids a cast
            Student student2 = context.getBean("student2", Student.class);
            Student student3 = context.getBean("student3", Student.class);

            System.out.println("\n<value> element:     " + student1);
            System.out.println("value attribute:     " + student2);
            System.out.println("p: namespace:        " + student3);
        }
    }
}
