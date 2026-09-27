package com.api.book.rest_first.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.book.rest_first.dao.BookRepository;
import com.api.book.rest_first.dto.BookRequest;
import com.api.book.rest_first.dto.BookResponse;
import com.api.book.rest_first.entities.Book;
import com.api.book.rest_first.exceptions.BookNotFoundException;

/*
 * SERVICE layer: business logic and the transaction boundary.
 * It speaks DTOs to the controller and entities to the repository, so neither side
 * depends on the other's details. @Service (not @Component) says what the class is for.
 */
@Service
@Transactional
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> findBooks(String author, Pageable pageable) {
        Page<Book> books = (author == null || author.isBlank())
                ? repository.findAll(pageable)
                : repository.findByAuthorContainingIgnoreCase(author, pageable);
        return books.map(BookResponse::from);
    }

    @Transactional(readOnly = true)
    public BookResponse findBook(int id) {
        return BookResponse.from(load(id));
    }

    public BookResponse create(BookRequest request) {
        return BookResponse.from(repository.save(new Book(request.title(), request.author())));
    }

    public BookResponse replace(int id, BookRequest request) {
        Book book = load(id);
        book.update(request.title(), request.author());   // saved at commit (dirty checking)
        return BookResponse.from(book);
    }

    public void delete(int id) {
        repository.delete(load(id));
    }

    private Book load(int id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }
}
