package com.bootcamp.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.productservice.model.Product;
import com.bootcamp.productservice.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService ps;
	
	@GetMapping("/Products")
	public List<Product> findAll(){
		
		return ps.findAll();
	}
	
	@GetMapping("/product/{pName}")
	public ResponseEntity<Product> findByName(@PathVariable String pName) {
	    return new ResponseEntity<>(ps.findByPName(pName), HttpStatus.OK);
	}

	
	
//	@GetMapping("/product/{pName}")
//	public ResponseEntity<Product> findByName(@PathVariable String pName) {
//		
//		return new ResponseEntity<Product>(ps.findByPName(pName), HttpStatus.OK);
		
	//}
	
	@PostMapping("/save")
	public ResponseEntity<Product> createProduct( @RequestBody Product p) {
		
		return new ResponseEntity<Product>(ps.saveProduct(p), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update/{pId}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer pId, @RequestBody Product pr) {
		
		return new ResponseEntity<Product>(ps.updateProduct(pId, pr), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{pId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer pId) {
		
		return new ResponseEntity<String>(ps.deleteProduct(pId), HttpStatus.OK);
		
	}
	
	
	
	

}
