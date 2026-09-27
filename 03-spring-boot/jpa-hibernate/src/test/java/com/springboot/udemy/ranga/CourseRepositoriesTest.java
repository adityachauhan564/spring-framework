package com.springboot.udemy.ranga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.springboot.udemy.ranga.topic01_spring_jdbc.CourseJdbcRepository;
import com.springboot.udemy.ranga.topic02_jpa_entity_manager.Course;
import com.springboot.udemy.ranga.topic02_jpa_entity_manager.CourseJpaRepository;
import com.springboot.udemy.ranga.topic03_spring_data_jpa.CourseSpringDataRepository;

/*
 * Test slices for the data layer:
 *   @JdbcTest    - a DataSource + JdbcTemplate + schema.sql, nothing else
 *   @DataJpaTest - JPA + Spring Data repositories + schema.sql, nothing else
 * Each test runs in a transaction that is ROLLED BACK afterwards, so tests never see each
 * other's data. The @Component runners don't run in slices either.
 */
class CourseRepositoriesTest {

    @Nested
    @JdbcTest
    @Import(CourseJdbcRepository.class)
    class SpringJdbc {

        @Autowired
        CourseJdbcRepository repository;

        @Test
        void insertFindDelete() {
            repository.insert(new Course(1, "Learn AWS", "in28minutes"));
            assertEquals("Learn AWS", repository.findById(1).orElseThrow().getName());
            repository.deleteById(1);
            assertTrue(repository.findById(1).isEmpty());
        }
    }

    @Nested
    @DataJpaTest
    @Import(CourseJpaRepository.class)
    class JpaEntityManager {

        @Autowired
        CourseJpaRepository repository;

        @Test
        void insertFindDelete() {
            repository.insert(new Course(11, "Learn Azure", "in28minutes"));
            assertEquals("in28minutes", repository.findById(11).orElseThrow().getAuthor());
            repository.deleteById(11);
            assertTrue(repository.findById(11).isEmpty());
        }
    }

    @Nested
    @DataJpaTest
    class SpringDataJpa {

        @Autowired
        CourseSpringDataRepository repository;

        private void seed() {
            repository.saveAll(List.of(
                    new Course(1, "Learn DevOps", "in28minutes"),
                    new Course(2, "Learn Docker", "in28minutes"),
                    new Course(3, "Learn AWS", "in28minutes"),
                    new Course(4, "Clean Code", "Robert Martin")));
        }

        @Test
        void derivedQueriesFromMethodNames() {
            seed();
            assertEquals(3, repository.findByAuthor("in28minutes").size());
            assertEquals(3, repository.countByAuthor("in28minutes"));
            assertEquals(3, repository.findByNameContainingIgnoreCase("LEARN").size());
        }

        @Test
        void customJpqlQuery() {
            seed();
            assertEquals(List.of("Clean Code"), repository.findAllExceptAuthor("in28minutes").stream().map(Course::getName).toList());
        }

        @Test
        void pagingAndSorting() {
            seed();
            Page<Course> firstPage = repository.findByAuthor("in28minutes", PageRequest.of(0, 2, Sort.by("name")));
            assertEquals(List.of("Learn AWS", "Learn DevOps"), firstPage.getContent().stream().map(Course::getName).toList());
            assertEquals(3, firstPage.getTotalElements());
            assertEquals(2, firstPage.getTotalPages());
        }
    }
}
