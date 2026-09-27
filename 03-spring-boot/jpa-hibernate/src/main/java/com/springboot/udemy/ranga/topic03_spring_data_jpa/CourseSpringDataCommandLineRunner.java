package com.springboot.udemy.ranga.topic03_spring_data_jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.springboot.udemy.ranga.topic02_jpa_entity_manager.Course;

@Component
@Order(3)
public class CourseSpringDataCommandLineRunner implements CommandLineRunner {

    private final CourseSpringDataRepository repository;

    public CourseSpringDataCommandLineRunner(CourseSpringDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== topic03: Spring Data JPA (no implementation written) ===");
        repository.save(new Course(21, "Learn Docker", "in28minutes"));
        repository.save(new Course(22, "Clean Code", "Robert Martin"));
        repository.save(new Course(23, "Learn Spring Boot", "in28minutes"));
        repository.deleteById(21L);
        System.out.println("findById(22): " + repository.findById(22L).orElseThrow());
        System.out.println("count(): " + repository.count() + " courses in the table (from all three topics)");
    }
}
