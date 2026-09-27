package com.jbdl63.digitallibrary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import com.jbdl63.digitallibrary.dto.UpdateAuthorDto;
import com.jbdl63.digitallibrary.repository.AuthorRepository;
import com.jbdl63.digitallibrary.service.AuthorService;

/*
 * Proves the cache annotations work: a spy wraps the real repository and counts its calls.
 * (The default profile uses the in-memory cache; the same annotations work unchanged with Redis.)
 */
@SpringBootTest
class CachingTest {

    @Autowired AuthorService authorService;
    @Autowired CacheManager cacheManager;
    @MockitoSpyBean AuthorRepository authorRepository;

    @BeforeEach
    void emptyCache() {
        cacheManager.getCache(AuthorService.CACHE).clear();
    }

    @Test
    void secondReadComesFromTheCache() {
        authorService.fetchAuthorDetailsByName("J.K. Rowling");
        authorService.fetchAuthorDetailsByName("J.K. Rowling");

        verify(authorRepository, times(1)).findByAuthorName("J.K. Rowling");   // the database was asked once
    }

    @Test
    void cachePutReplacesTheCachedCopy() {
        authorService.fetchAuthorDetailsByName("R.K. Narayan");
        authorService.updateAuthorAddress(new UpdateAuthorDto(2, "Chennai"));

        assertThat(authorService.fetchAuthorDetailsByName("R.K. Narayan").getAuthorAddress()).isEqualTo("Chennai");
        verify(authorRepository, times(1)).findByAuthorName("R.K. Narayan");   // still no second query

        authorService.updateAuthorAddress(new UpdateAuthorDto(2, "Mysore"));   // put the sample data back
    }
}
