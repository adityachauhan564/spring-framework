package com.jbdl63.digitallibrary;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.jayway.jsonpath.JsonPath;

/*
 * The whole app over HTTP (MockMvc, no real server), on H2 with the data.sql sample data.
 * No @Transactional here on purpose: every request commits like a real one, so database rule
 * errors (the 409s) really happen. Each test creates its own users, so it doesn't depend on the others.
 */
@SpringBootTest
@AutoConfigureMockMvc
class LibraryApiTest {

    @Autowired MockMvc mvc;

    @Test
    void authorsAreFoundByNameOr404() throws Exception {
        mvc.perform(get("/v1/authors/{name}", "R.K. Narayan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorAddress").value("Mysore"))
                .andExpect(jsonPath("$.booksList").doesNotExist());     // @JsonIgnore
        mvc.perform(get("/v1/authors/usingParam").param("authorName", "nobody"))
                .andExpect(status().isNotFound());
    }

    @Test
    void invalidBodyIs400WithTheFieldAndDuplicateIs409() throws Exception {
        mvc.perform(post("/v1/authors").contentType(MediaType.APPLICATION_JSON).content("{\"authorName\":\" \"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.authorName").value("Author Name should not be blank"));
        mvc.perform(post("/v1/authors").contentType(MediaType.APPLICATION_JSON).content("{\"authorName\":\"R.K. Narayan\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void booksAreFilteredByAuthorOrCategory() throws Exception {
        mvc.perform(get("/v1/books").param("author", "J.K. Rowling")).andExpect(jsonPath("$", hasSize(2)));
        mvc.perform(get("/v1/books").param("category", "fiction")).andExpect(jsonPath("$[0].bookName").value("Malgudi Days"));
        mvc.perform(get("/v1/books")).andExpect(status().isBadRequest());
    }

    @Test
    void aBookNeedsAnExistingAuthor() throws Exception {
        String book = """
                {"bookName":"New","publicationYear":"2024","bookPrice":100,"bookEdition":"1st",
                 "bookCategory":"TECH","author":{"authorId":%d}}""";
        mvc.perform(post("/v1/books").contentType(MediaType.APPLICATION_JSON).content(book.formatted(2)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.author.authorName").value("R.K. Narayan"));
        mvc.perform(post("/v1/books").contentType(MediaType.APPLICATION_JSON).content(book.formatted(999)))
                .andExpect(status().isNotFound());
    }

    @Test
    void issueAndReturnABook() throws Exception {
        int userId = newUser("ravi");

        mvc.perform(post("/v1/users/{u}/books/{b}", userId, 1)).andExpect(jsonPath("$", hasSize(1)));
        mvc.perform(post("/v1/users/{u}/books/{b}", userId, 1)).andExpect(status().isConflict());       // twice
        mvc.perform(get("/v1/users/{u}/books", userId)).andExpect(jsonPath("$[0].bookId").value(1));
        mvc.perform(delete("/v1/users/{u}", userId)).andExpect(status().isConflict());                   // still has a book

        mvc.perform(delete("/v1/users/{u}/books/{b}", userId, 1)).andExpect(jsonPath("$", hasSize(0)));
        mvc.perform(delete("/v1/users/{u}/books/{b}", userId, 1)).andExpect(status().isNotFound());      // not issued
        mvc.perform(delete("/v1/users/{u}", userId)).andExpect(status().isNoContent());
    }

    @Test
    void anIssuedBookCannotBeDeleted() throws Exception {
        int userId = newUser("meera");
        mvc.perform(post("/v1/users/{u}/books/{b}", userId, 3)).andExpect(status().isOk());

        mvc.perform(delete("/v1/books/{b}", 3)).andExpect(status().isConflict());   // books_issued still points to it

        mvc.perform(delete("/v1/users/{u}/books/{b}", userId, 3)).andExpect(status().isOk());
    }

    private int newUser(String name) throws Exception {
        String json = mvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"%s\",\"userMobileNo\":\"123\",\"userEmailId\":\"%s@example.com\"}".formatted(name, name)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return JsonPath.read(json, "$.userId");
    }
}
