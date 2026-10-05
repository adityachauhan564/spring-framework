package com.bootcamp.productservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.bootcamp.productservice.model.Product;
import com.bootcamp.productservice.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public List<Product> findAll(String search) {
		return StringUtils.hasText(search)
				? repository.findByNameContainingIgnoreCaseOrderByName(search.trim())
				: repository.findAllByOrderByName();
	}

	public Product findById(Integer id) {
		// ResponseStatusException is the quickest correct way to send a 404. A plain RuntimeException would become a 500
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No product with id " + id));
	}

	public Product create(Product product) {
		product.setId(null);                   // always a new row, whatever id the client sent
		return repository.save(product);
	}

	// PUT replaces the product's fields (every field is checked as required).
	// Inside @Transactional, changes to the loaded entity are saved by themselves at commit: no save() needed.
	@Transactional
	public Product update(Integer id, Product changes) {
		Product existing = findById(id);
		existing.setName(changes.getName());
		existing.setPrice(changes.getPrice());
		existing.setQuantity(changes.getQuantity());
		return existing;
	}

	public void delete(Integer id) {
		repository.delete(findById(id));
	}
}
