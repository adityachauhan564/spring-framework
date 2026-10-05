package com.springcore.topic07_component_scanning;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * @Component = "Spring, please create a bean of this class for me". No XML <bean> needed.
 * The default bean name is the class name with a small first letter: "student".
 * @Value puts a fixed value into the field
 * (topic10 uses it for SpEL expressions, topic12 for values from a properties file).
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
