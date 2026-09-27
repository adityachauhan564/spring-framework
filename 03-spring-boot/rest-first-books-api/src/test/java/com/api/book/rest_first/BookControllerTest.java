package com.api.book.rest_first;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.api.book.rest_first.controllers.BookController;
import com.api.book.rest_first.dto.BookRequest;
import com.api.book.rest_first.dto.BookResponse;
import com.api.book.rest_first.exceptions.BookNotFoundException;
import com.api.book.rest_first.services.BookService;

/*
 * Only the web layer. @MockitoBean replaces the real BookService with a Mockito mock, so this
 * test checks HTTP behaviour (status codes, JSON, validation) without any database.
 * when(...).thenReturn(...) scripts the mock; verify(...) checks how it was called.
 */
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    BookService bookService;

    @Test
    void getBookReturnsTheDto() throws Exception {
        when(bookService.findBook(1)).thenReturn(new BookResponse(1, "Head First Java", "Kathy Sierra"));
        mvc.perform(get("/books/1")).andExpect(status().isOk()).andExpect(jsonPath("$.title").value("Head First Java"));
    }

    @Test
    void missingBookIs404() throws Exception {
        when(bookService.findBook(99)).thenThrow(new BookNotFoundException(99));
        mvc.perform(get("/books/99")).andExpect(status().isNotFound()).andExpect(jsonPath("$.title").value("Book not found"));
    }

    @Test
    void createReturns201AndLocation() throws Exception {
        when(bookService.create(any(BookRequest.class))).thenReturn(new BookResponse(8, "Refactoring", "Martin Fowler"));
        mvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"Refactoring\",\"author\":\"Martin Fowler\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/books/8"));
        verify(bookService).create(eq(new BookRequest("Refactoring", "Martin Fowler")));
    }

    @Test
    void invalidBodyIs400AndNeverReachesTheService() throws Exception {
        mvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"\",\"author\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.title").value("title is required"));
        verify(bookService, never()).create(any());
    }
}
