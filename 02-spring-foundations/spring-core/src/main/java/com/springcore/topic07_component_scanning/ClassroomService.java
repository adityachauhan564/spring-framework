package com.springcore.topic07_component_scanning;

import org.springframework.stereotype.Service;

/*
 * @Service, @Repository and @Controller are all just @Component with a job title.
 * Spring creates the bean the same way for all of them, but the name tells readers
 * (and some Spring features) what the class does. Like "cashier" vs "manager" on a name badge.
 * Only one constructor -> Spring injects into it automatically, no @Autowired needed.
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
