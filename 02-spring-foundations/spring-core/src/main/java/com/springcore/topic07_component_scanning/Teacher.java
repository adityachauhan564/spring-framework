package com.springcore.topic07_component_scanning;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/* A custom bean name: @Component("headTeacher") instead of the default "teacher". */
@Component("headTeacher")
public class Teacher {

    @Value("Mrs. Sharma")
    private String name;

    @Override
    public String toString() {
        return "Teacher[name=" + name + "]";
    }
}
