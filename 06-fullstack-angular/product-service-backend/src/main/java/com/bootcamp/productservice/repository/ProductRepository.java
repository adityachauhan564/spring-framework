package com.bootcamp.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.productservice.model.Product;

// JpaRepository<entity, id type>: save, findById, findAll, delete... are all provided
public interface ProductRepository extends JpaRepository<Product, Integer> {

	// Derived query for the search box: where lower(name) like lower('%text%') order by name
	List<Product> findByNameContainingIgnoreCaseOrderByName(String text);

	List<Product> findAllByOrderByName();
}
