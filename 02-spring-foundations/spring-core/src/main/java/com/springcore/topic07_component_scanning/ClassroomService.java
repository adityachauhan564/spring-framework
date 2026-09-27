package com.springcore.topic07_component_scanning;

import org.springframework.stereotype.Service;

/*
 * @Service, @Repository and @Controller are all @Component with a role name:
 * same bean creation, but they tell readers (and some Spring features) what the class does.
 * One constructor -> Spring injects it automatically, no @Autowired needed.
 */
@Service
public class ClassroomService {

    private final Student student;
    private final Teacher teacher;

    public ClassroomService(Student student, Teacher teacher) {
        this.student = student;
        this.teacher = teacher;
    }

    public String describe() {
        return teacher + " teaches " + student;
    }
}
