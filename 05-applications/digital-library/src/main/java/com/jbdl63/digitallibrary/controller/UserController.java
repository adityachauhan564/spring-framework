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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jbdl63.digitallibrary.model.Book;
import com.jbdl63.digitallibrary.model.User;
import com.jbdl63.digitallibrary.service.UserService;

import jakarta.validation.Valid;

/*
 * Issued books are a sub-resource of the user (they live "under" a user in the URL),
 * like a library card that lists the books you have borrowed:
 *   GET    /v1/users/{id}/books           the books this user has
 *   POST   /v1/users/{id}/books/{bookId}  issue a book    (409 if they already have it)
 *   DELETE /v1/users/{id}/books/{bookId}  return it       (404 if they don't have it)
 */
@RestController
@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User addNewUser(@RequestBody @Valid User user) {
        return userService.addNewUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Integer userId) {
        return userService.findById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Integer userId, @RequestBody @Valid User user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}/books")
    public List<Book> issuedBooks(@PathVariable Integer userId) {
        return userService.findAllBooksIssuedToUser(userId);
    }

    @PostMapping("/{userId}/books/{bookId}")
    public List<Book> issueBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        return userService.issueBook(userId, bookId);
    }

    @DeleteMapping("/{userId}/books/{bookId}")
    public List<Book> returnBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        return userService.returnBook(userId, bookId);
    }
}
