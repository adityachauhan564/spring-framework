package com.springcore.topic07_component_scanning;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * @Component = "Spring, create a bean of this class for me" - no XML <bean> needed.
 * The default bean name is the class name with a lower-case first letter: "student".
 * @Value injects a literal (topic10 uses it for SpEL, topic12 for properties).
 */
@Component
public class Student {

    @Value("Aditya Chauhan")
    private String studentName;

    @Value("Lucknow")
    private String city;

    @Override
    public String toString() {
        return "Student[name=" + studentName + ", city=" + city + "]";
    }
}
