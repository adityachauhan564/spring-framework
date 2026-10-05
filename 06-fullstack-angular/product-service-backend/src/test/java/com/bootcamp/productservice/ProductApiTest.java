package com.bootcamp.productservice;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.jayway.jsonpath.JsonPath;

/*
 * The whole API over HTTP (MockMvc) on H2 with the data.sql sample products.
 * @DirtiesContext: the tests change the data, so each test method gets a fresh application (and a fresh database).
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductApiTest {

	@Autowired MockMvc mvc;

	@Test
	void listIsSortedByNameWithPlainJsonKeys() throws Exception {
		mvc.perform(get("/api/products"))
				.andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].name").value("Ball pen"))     // "name", not "pname"
				.andExpect(jsonPath("$[0].price").value(10.0));
	}

	@Test
	void searchIgnoresCase() throws Exception {
		mvc.perform(get("/api/products").param("search", "LAMP"))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name").value("Desk lamp"));
	}

	@Test
	void createUpdateDeleteRoundTrip() throws Exception {
		String created = mvc.perform(json(post("/api/products"), "{\"name\":\"Stapler\",\"price\":150,\"quantity\":8}"))
				.andExpect(status().isCreated())
				.andReturn().getResponse().getContentAsString();
		int id = JsonPath.read(created, "$.id");

		mvc.perform(json(put("/api/products/{id}", id), "{\"name\":\"Stapler XL\",\"price\":175,\"quantity\":6}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Stapler XL"));

		mvc.perform(delete("/api/products/{id}", id)).andExpect(status().isNoContent());
		mvc.perform(get("/api/products/{id}", id)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.detail").value("No product with id " + id));
	}

	@Test
	void invalidProductIs400WithAMessagePerField() throws Exception {
		mvc.perform(json(post("/api/products"), "{\"name\":\"\",\"price\":-1}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors.name").value("Name is required"))
				.andExpect(jsonPath("$.errors.price").value("Price can't be negative"))
				.andExpect(jsonPath("$.errors.quantity").value("Quantity is required"));
	}

	@Test
	void updatingOrDeletingAMissingProductIs404() throws Exception {
		mvc.perform(json(put("/api/products/999"), "{\"name\":\"X\",\"price\":1,\"quantity\":1}")).andExpect(status().isNotFound());
		mvc.perform(delete("/api/products/999")).andExpect(status().isNotFound());
	}

	// What the browser does before a cross-origin PUT: it first asks permission with an OPTIONS "preflight" request
	@Test
	void corsAllowsOnlyTheAngularDevServer() throws Exception {
		mvc.perform(options("/api/products/1")
						.header("Origin", "http://localhost:4200")
						.header("Access-Control-Request-Method", "PUT"))
				.andExpect(status().isOk())
				.andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:4200"));

		mvc.perform(options("/api/products/1")
						.header("Origin", "http://evil.example")
						.header("Access-Control-Request-Method", "DELETE"))
				.andExpect(status().isForbidden());
	}

	private static MockHttpServletRequestBuilder json(MockHttpServletRequestBuilder request, String body) {
		return request.contentType(MediaType.APPLICATION_JSON).content(body);
	}
}
