package com.bootcamp.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.bootcamp.productservice.model.Product;
import com.bootcamp.productservice.service.ProductService;

import jakarta.validation.Valid;

/*
 * REST style: one resource URL, the HTTP method says what happens.
 *   GET    /api/products?search=pen   list (optionally filtered)     200
 *   GET    /api/products/{id}         one product                     200 / 404
 *   POST   /api/products              create                          201 / 400
 *   PUT    /api/products/{id}         replace                         200 / 400 / 404
 *   DELETE /api/products/{id}         delete                          204 / 404
 * The course used verbs in the paths (/save, /update/{id}, /delete/{id}): the method already says that.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<Product> findAll(@RequestParam(required = false) String search) {
		return productService.findAll(search);
	}

	@GetMapping("/{id}")
	public Product findById(@PathVariable Integer id) {
		return productService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody @Valid Product product) {
		return productService.create(product);
	}

	@PutMapping("/{id}")
	public Product update(@PathVariable Integer id, @RequestBody @Valid Product product) {
		return productService.update(id, product);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)     // success, and nothing to send back
	public void delete(@PathVariable Integer id) {
		productService.delete(id);
	}
}
