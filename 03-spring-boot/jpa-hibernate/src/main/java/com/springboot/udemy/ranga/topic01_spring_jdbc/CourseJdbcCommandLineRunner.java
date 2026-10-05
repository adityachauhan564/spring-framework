package com.springboot.udemy.ranga.topic01_spring_jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.springboot.udemy.ranga.topic02_jpa_entity_manager.Course;

/*
 * Runs once, at startup. Each topic has a runner like this one, and each uses its own ids
 * so they don't clash (topic01: 1-3, topic02: 11-13, topic03: 21-23, topic04 reads everything).
 */
@Component
@Order(1)
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    private final CourseJdbcRepository repository;

    public CourseJdbcCommandLineRunner(CourseJdbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== topic01: Spring JDBC (you write the SQL) ===");
        repository.insert(new Course(1, "Learn AWS", "in28minutes"));
        repository.insert(new Course(2, "Head First Design Patterns", "Eric Freeman"));
        repository.insert(new Course(3, "Learn DevOps", "in28minutes"));
        repository.deleteById(1);
        System.out.println("findById(2): " + repository.findById(2).orElseThrow());
        System.out.println("findById(1) after delete: " + repository.findById(1).map(Course::toString).orElse("not found"));
    }
}
