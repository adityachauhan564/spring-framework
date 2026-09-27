package com.jbdl63.digitallibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jbdl63.digitallibrary.dto.UpdateAuthorDto;
import com.jbdl63.digitallibrary.exception.BadRequestException;
import com.jbdl63.digitallibrary.exception.DataNotFoundException;
import com.jbdl63.digitallibrary.model.Author;
import com.jbdl63.digitallibrary.repository.AuthorRepository;

/*
 * Caching: fetching an author by name is cached, with the name as the key.
 *   @Cacheable  - the first call runs the method and stores the result; later calls return the stored copy
 *   @CachePut   - always runs the method, then REPLACES the stored copy (same key!) with the result
 *   @CacheEvict - removes entries, so the next read goes to the database again
 * The annotations sit on the service, not the controller, so every caller gets the same caching.
 */
@Service
public class AuthorService {

    public static final String CACHE = "authors";

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author addNewAuthor(Author author) {
        author.setAuthorId(null);             // always a new row, whatever id the client sent
        return authorRepository.save(author); // a duplicate name breaks the unique constraint -> 409
    }

    @Cacheable(value = CACHE, key = "#authorName")
    public Author fetchAuthorDetailsByName(String authorName) {
        return authorRepository.findByAuthorName(authorName)
                .orElseThrow(() -> new DataNotFoundException("Author not found: " + authorName));
    }

    public List<Author> fetchAllAvailableAuthors() {
        return authorRepository.findAll();
    }

    @Transactional
    @CachePut(value = CACHE, key = "#result.authorName")   // same key as @Cacheable above
    public Author updateAuthorAddress(UpdateAuthorDto update) {
        Author author = authorRepository.findById(update.authorId())
                .orElseThrow(() -> new DataNotFoundException("Author not found: " + update.authorId()));
        author.setAuthorAddress(update.address());
        return author;   // inside a transaction, changes to a loaded entity are saved at commit - no save() needed
    }

    // The cache key is the name but only the id is known here, so the simple correct choice is to clear all
    @CacheEvict(value = CACHE, allEntries = true)
    public void deleteById(Integer authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new DataNotFoundException("Author not found: " + authorId);
        }
        authorRepository.deleteById(authorId);
    }

    /** CSV with a header line, then one "authorName,authorAddress" per line. Ids are generated. */
    public List<Author> uploadAuthorsDataToDatabase(String fileContent) {
        String[] lines = fileContent.split("\\r?\\n");   // \r?\n also handles Windows line endings
        List<Author> authors = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {          // i = 1 skips the header
            if (lines[i].isBlank()) continue;
            String[] row = lines[i].split(",");
            if (row.length != 2) {
                throw new BadRequestException("Line " + (i + 1) + " needs 2 columns: authorName,authorAddress");
            }
            authors.add(Author.builder().authorName(row[0].trim()).authorAddress(row[1].trim()).build());
        }
        return authorRepository.saveAll(authors);
    }
}
