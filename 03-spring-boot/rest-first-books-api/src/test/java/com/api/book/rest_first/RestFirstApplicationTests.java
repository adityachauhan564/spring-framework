package com.api.book.rest_first;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/*
 * End to end, through every layer (controller -> service -> repository -> H2).
 * The old version of this test needed a running MySQL, so `./mvnw test` failed on any machine
 * that didn't have one. Now the default database is in-memory H2.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RestFirstApplicationTests {

    @Autowired
    MockMvc mvc;

    @Test
    void fullCrudLifecycle() throws Exception {
        String created = mvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Refactoring\",\"author\":\"Martin Fowler\"}"))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        int id = Integer.parseInt(created.replaceAll(".*\"id\":(\\d+).*", "$1"));

        mvc.perform(get("/books/" + id)).andExpect(jsonPath("$.author").value("Martin Fowler"));
        mvc.perform(put("/books/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Refactoring (2nd ed.)\",\"author\":\"Martin Fowler\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.title").value("Refactoring (2nd ed.)"));
        mvc.perform(delete("/books/" + id)).andExpect(status().isNoContent());
        mvc.perform(get("/books/" + id)).andExpect(status().isNotFound());
    }

    @Test
    void listIsPagedAndSortable() throws Exception {
        mvc.perform(get("/books").param("size", "2").param("sort", "title,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].title").value("Clean Code"))
                .andExpect(jsonPath("$.page.size").value(2));
    }
}
