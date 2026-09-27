package com.jbdl63.digitallibrary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jbdl63.digitallibrary.exception.BadRequestException;
import com.jbdl63.digitallibrary.model.Book;
import com.jbdl63.digitallibrary.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/v1/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addNewBook(@RequestBody @Valid Book book) {
        return bookService.addNewBook(book);
    }

    @GetMapping("/{bookId}")
    public Book getBook(@PathVariable Integer bookId) {
        return bookService.findById(bookId);
    }

    // Filters are query parameters on the collection:  /v1/books?author=R.K. Narayan  or  ?category=fantasy
    @GetMapping
    public List<Book> findBooks(@RequestParam(required = false) String author,
                                @RequestParam(required = false) String category) {
        if (author != null) return bookService.findBooksByAuthorName(author);
        if (category != null) return bookService.findBooksByCategory(category);
        throw new BadRequestException("Filter by ?author= or ?category=");
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Integer bookId, @RequestBody @Valid Book book) {
        return bookService.updateBook(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Integer bookId) {
        bookService.deleteBookById(bookId);
    }
}
