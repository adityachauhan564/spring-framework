package com.api.book.rest_first.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.book.rest_first.entities.Book;

/*
 * DATA layer: Spring Data writes the implementation (see jpa-hibernate topic03).
 * JpaRepository already has findById(Integer) returning Optional<Book>. The old version added
 * its own findById(int) returning Book - an overload that hid the Optional one and invited
 * NullPointerExceptions. It's gone; use the inherited method.
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
}
