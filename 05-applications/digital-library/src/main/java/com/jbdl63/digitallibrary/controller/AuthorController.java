package com.jbdl63.digitallibrary.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbdl63.digitallibrary.dto.UpdateAuthorDto;
import com.jbdl63.digitallibrary.model.Author;
import com.jbdl63.digitallibrary.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/v1/authors", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author addNewAuthor(@RequestBody @Valid Author author) {
        return authorService.addNewAuthor(author);
    }

    @GetMapping
    public List<Author> fetchAllAvailableAuthors() {
        return authorService.fetchAllAvailableAuthors();
    }

    // Two ways to take the same input:  /v1/authors/J.K. Rowling  vs  /v1/authors/usingParam?authorName=...
    @GetMapping("/{authorName}")
    public Author fetchAuthorByName(@PathVariable String authorName) {
        return authorService.fetchAuthorDetailsByName(authorName);
    }

    @GetMapping("/usingParam")
    public Author fetchAuthorByNameUsingParam(@RequestParam String authorName) {
        return authorService.fetchAuthorDetailsByName(authorName);
    }

    @PutMapping
    public Author updateAuthorAddress(@RequestBody @Valid UpdateAuthorDto updateAuthorDto) {
        return authorService.updateAuthorAddress(updateAuthorDto);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Integer authorId) {
        authorService.deleteById(authorId);
    }

    // curl -F "file=@authors.csv" .../v1/authors/upload-csv   (header line, then authorName,authorAddress)
    @PostMapping("/upload-csv")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Author> uploadAuthors(@RequestPart("file") MultipartFile file) throws IOException {
        return authorService.uploadAuthorsDataToDatabase(new String(file.getBytes(), StandardCharsets.UTF_8));
    }
}
