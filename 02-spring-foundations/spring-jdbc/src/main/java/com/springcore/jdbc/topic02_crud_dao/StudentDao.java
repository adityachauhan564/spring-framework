package com.springcore.jdbc.topic02_crud_dao;

import java.util.List;
import java.util.Optional;

/*
 * The contract (a list of promises). Callers depend only on this interface, never on SQL.
 * So you could swap in a Hibernate (spring-orm) or Spring Data (stage 03) version
 * without changing a single caller.
 */
public interface StudentDao {

    int insert(Student student);

    int update(Student student);

    int delete(int studentId);

    Optional<Student> findById(int studentId);   // Optional, because a student with this id may not exist

    List<Student> findAll();
}
