package com.springcore.jdbc.topic02_crud_dao;

import java.util.List;
import java.util.Optional;

/*
 * The contract: callers depend on this interface, never on SQL. You could swap in a
 * Hibernate (spring-orm) or Spring Data (stage 03) version without changing them.
 */
public interface StudentDao {

    int insert(Student student);

    int update(Student student);

    int delete(int studentId);

    Optional<Student> findById(int studentId);   // Optional: the student may not exist

    List<Student> findAll();
}
