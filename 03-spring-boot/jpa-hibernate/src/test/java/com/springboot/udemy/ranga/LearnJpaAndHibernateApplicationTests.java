package com.springboot.udemy.ranga;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.udemy.ranga.topic03_spring_data_jpa.CourseSpringDataRepository;

/* Starts the whole app, with every startup runner: 3 topics x 3 inserts, minus 3 deletes = 6 rows left. */
@SpringBootTest
class LearnJpaAndHibernateApplicationTests {

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    void startupRunnersLeaveSixCourses() {
        assertEquals(6, repository.count());
    }
}
