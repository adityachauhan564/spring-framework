package com.jbdl63.digitallibrary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbdl63.digitallibrary.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    // Derived query: Spring Data writes "where author_name = ?" from the method name
    Optional<Author> findByAuthorName(String authorName);
}
