package com.bootcamp.productservice.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bootcamp.productservice.model.Product;

//T= Entity Name, ID(Type)
public interface ProductRepository extends CrudRepository<Product,Integer> {
	
	// to handle nullpointer exception Optional is being used 
	public Optional<Product> findBypName(String pName);

}
