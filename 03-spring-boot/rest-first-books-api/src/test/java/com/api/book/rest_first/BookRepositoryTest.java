package com.api.book.rest_first;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.api.book.rest_first.dao.BookRepository;
import com.api.book.rest_first.entities.Book;

/* Only the data layer, on H2, with the 7 sample books from data.sql. Rolled back after each test. */
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Test
    void sampleDataIsLoaded() {
        assertEquals(7, repository.count());
    }

    @Test
    void authorSearchIsCaseInsensitiveAndPaged() {
        repository.save(new Book("Head First Servlets", "Kathy Sierra"));
        Page<Book> page = repository.findByAuthorContainingIgnoreCase("SIERRA", PageRequest.of(0, 1, Sort.by("title")));
        assertEquals("Head First Java", page.getContent().get(0).getTitle());
        assertEquals(2, page.getTotalElements());
    }
}
