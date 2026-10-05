package com.springboot.udemy.ranga.topic03_spring_data_jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.udemy.ranga.topic02_jpa_entity_manager.Course;

/*
 * Topic    : Spring Data JPA - you write NO implementation at all
 * Key idea : - Just extend JpaRepository<Entity, IdType>, and Spring writes the class for you at startup.
 *            - save, findById, findAll, deleteById, count, existsById... are all ready to use.
 *            - Topic02's CourseJpaRepository, written by hand, is now just ONE interface.
 *            - Like buying a ready-made shirt instead of stitching one yourself.
 *
 * Topic04  : query methods - Spring reads the METHOD NAME and writes the query:
 *              findByAuthor(a)                   -> where author = ?
 *              findByNameContainingIgnoreCase(t) -> where upper(name) like upper('%t%')
 *              countByAuthor(a)                  -> select count(*) ... where author = ?
 *            @Query for anything a name can't express, written in JPQL (entity/field names).
 *            A Pageable parameter adds paging and sorting to any query.
 */
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByAuthor(String author);

    List<Course> findByNameContainingIgnoreCase(String text);

    long countByAuthor(String author);

    Page<Course> findByAuthor(String author, Pageable pageable);

    @Query("select c from Course c where c.author <> :author order by c.name")
    List<Course> findAllExceptAuthor(String author);
}
