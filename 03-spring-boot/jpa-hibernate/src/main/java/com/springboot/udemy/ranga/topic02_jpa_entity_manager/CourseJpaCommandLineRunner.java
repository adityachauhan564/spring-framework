package com.springboot.udemy.ranga.topic02_jpa_entity_manager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CourseJpaCommandLineRunner implements CommandLineRunner {

    private final CourseJpaRepository repository;

    public CourseJpaCommandLineRunner(CourseJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== topic02: JPA EntityManager (Hibernate writes the SQL) ===");
        repository.insert(new Course(11, "Learn Azure", "in28minutes"));
        repository.insert(new Course(12, "Learn Kubernetes", "in28minutes"));
        repository.insert(new Course(13, "Effective Java", "Joshua Bloch"));
        repository.deleteById(11);
        System.out.println("findById(13): " + repository.findById(13).orElseThrow());
    }
}
