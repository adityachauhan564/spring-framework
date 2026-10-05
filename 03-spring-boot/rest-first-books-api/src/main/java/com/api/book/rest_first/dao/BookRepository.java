package com.api.book.rest_first.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.book.rest_first.entities.Book;

/*
 * DATA layer: Spring Data writes the code for you (see jpa-hibernate topic03).
 * JpaRepository already has findById(Integer), which returns Optional<Book>.
 * The old version added its own findById(int) that returned a plain Book - a second method with
 * the same name, which hid the Optional one and easily caused NullPointerExceptions.
 * It has been removed. Use the method you get from JpaRepository.
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
}
