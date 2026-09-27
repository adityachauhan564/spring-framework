package com.spring.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.spring.orm.topic01_entity_mapping.Student;
import com.spring.orm.topic05_jpa_entity_manager.JpaConfig;
import com.spring.orm.topic05_jpa_entity_manager.StudentJpaRepository;

@SpringJUnitConfig(JpaConfig.class)
@TestPropertySource(properties = "DB_URL=")
class JpaRepositoryTest {

    @Autowired
    StudentJpaRepository repository;

    @Test
    void crudThroughTheEntityManager() {
        Student asha = repository.save(new Student("Asha", "Pune"));
        repository.save(new Student("Meera", "Pune"));
        repository.save(new Student("Ravi", "Delhi"));

        assertEquals(3, repository.count());
        assertEquals("Asha", repository.findById(asha.getStudentId()).orElseThrow().getStudentName());
        assertEquals(2, repository.findByCity("Pune").size());

        repository.delete(asha.getStudentId());
        assertEquals(2, repository.count());
    }
}
