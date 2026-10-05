package com.springboot.udemy.ranga.topic02_jpa_entity_manager;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/*
 * Topic    : JPA with EntityManager - Hibernate writes the SQL
 * Key idea : - Map the class once (@Entity), then work only with OBJECTS: merge, find, remove.
 *              No SQL written by you.
 *            - Compare 02-spring-foundations/spring-orm topic05: there you also wrote the
 *              EntityManagerFactory and transaction manager beans. Here Boot creates them.
 *            - Look at the console: spring.jpa.show-sql prints every statement Hibernate sends.
 */
@Repository
@Transactional
public class CourseJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void insert(Course course) {
        entityManager.merge(course);          // merge = insert if new, update if it exists (we set the id ourselves)
    }

    @Transactional(readOnly = true)
    public Optional<Course> findById(long id) {
        return Optional.ofNullable(entityManager.find(Course.class, id));
    }

    public void deleteById(long id) {
        findById(id).map(entityManager::merge).ifPresent(entityManager::remove);
    }
}
