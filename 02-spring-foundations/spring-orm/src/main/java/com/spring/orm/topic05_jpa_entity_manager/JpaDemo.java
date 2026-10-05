package com.spring.orm.topic05_jpa_entity_manager;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.orm.topic01_entity_mapping.Student;

/*
 * Run      : ./mvnw -q -pl spring-orm compile exec:java -Dexec.mainClass=com.spring.orm.topic05_jpa_entity_manager.JpaDemo
 * Key idea : Same results as topic03, but through the standard JPA API.
 * Next     : 03-spring-boot/jpa-hibernate, where Spring Data JPA writes this repository for you.
 */
public class JpaDemo {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(JpaConfig.class)) {
            StudentJpaRepository repository = context.getBean(StudentJpaRepository.class);

            Student asha = repository.save(new Student("Asha", "Pune"));
            repository.save(new Student("Ravi", "Delhi"));
            repository.save(new Student("Meera", "Pune"));

            System.out.println("count:            " + repository.count());
            System.out.println("findById:         " + repository.findById(asha.getStudentId()).orElseThrow());
            System.out.println("findByCity(Pune): " + repository.findByCity("Pune"));

            repository.delete(asha.getStudentId());
            System.out.println("after delete, count = " + repository.count());
        }
    }
}
