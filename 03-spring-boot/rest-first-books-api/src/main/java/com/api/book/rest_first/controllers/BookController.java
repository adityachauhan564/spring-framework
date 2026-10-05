package com.api.book.rest_first.controllers;

import java.net.URI;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.rest_first.dto.BookRequest;
import com.api.book.rest_first.dto.BookResponse;
import com.api.book.rest_first.services.BookService;

import jakarta.validation.Valid;

/*
 * CONTROLLER layer: only HTTP work - read the request, call the service, choose the status code.
 *   GET    /books?author=&page=&size=&sort=   200, one page of books
 *   GET    /books/{id}                        200 or 404
 *   POST   /books                             201 + Location, or 400
 *   PUT    /books/{id}                        200, 400 or 404
 *   DELETE /books/{id}                        204 or 404
 * Paging: Spring fills Pageable from ?page=0&size=3&sort=title,desc (page numbers start at 0).
 * PagedModel gives JSON whose shape never changes: {"content":[...], "page":{"size","number","totalElements","totalPages"}}.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public PagedModel<BookResponse> getBooks(@RequestParam(required = false) String author,
                                             @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return new PagedModel<>(bookService.findBooks(author, pageable));
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable int id) {
        return bookService.findBook(id);
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookRequest request) {
        BookResponse created = bookService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.id()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public BookResponse replaceBook(@PathVariable int id, @Valid @RequestBody BookRequest request) {
        return bookService.replace(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
