package com.spring.orm.topic05_jpa_entity_manager;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.orm.topic01_entity_mapping.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/*
 * The same repository as topic03, written only with jakarta.persistence types.
 * @PersistenceContext injects a proxy that hands each transaction its own EntityManager
 * (so this singleton bean is safe to share between threads).
 * JPQL, like HQL, queries entity and field names.
 */
@Repository
@Transactional
public class StudentJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Student save(Student student) {
        entityManager.persist(student);
        return student;
    }

    @Transactional(readOnly = true)
    public Optional<Student> findById(int id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    @Transactional(readOnly = true)
    public List<Student> findByCity(String city) {
        return entityManager.createQuery("select s from Student s where s.studentCity = :city order by s.studentId", Student.class)
                .setParameter("city", city)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long count() {
        return entityManager.createQuery("select count(s) from Student s", Long.class).getSingleResult();
    }

    public void delete(int id) {
        findById(id).ifPresent(entityManager::remove);
    }
}
