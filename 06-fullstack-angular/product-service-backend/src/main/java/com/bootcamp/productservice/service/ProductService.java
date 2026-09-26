package com.bootcamp.productservice.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.productservice.dao.ProductRepository;
import com.bootcamp.productservice.model.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository prRepo;
	
	//return type is here *list of Product*
	public List<Product> findAll(){
		
		return (List<Product>) prRepo.findAll();
	}
	
	//find by name
	public Product findByPName(String pName) {
		
		return prRepo.findBypName(pName)
				.orElseThrow(()->new RuntimeException("Product Not Found "));
	}
	
	//save product
	public Product saveProduct(Product p) {
		
		return prRepo.save(p);
	}
	
	//update product ,first find the product then update the product 
	public Product updateProduct(Integer  pId, Product pr) {
		
	   Product existing=	prRepo.findById(pId)
		.orElseThrow(()->new RuntimeException("No Product Available with ID :: "+pId));
	   
	   if(pr.getPName()!=null)
		   existing.setPName(pr.getPName());
	   if(pr.getPPrice()!=null)
		   existing.setPPrice(pr.getPPrice());
	   if(pr.getPQuantity()!=null)
		   existing.setPQuantity(pr.getPQuantity());
	   
	   return prRepo.save(existing);
	}
	
	//delete the product
	public String deleteProduct(Integer pId) {
		
		Product existing=	prRepo.findById(pId)
				.orElseThrow(()->new RuntimeException("No Product Available with ID :: "+pId));
		
		//since product is already fetched so I am deleting it
		prRepo.delete(existing);
		
		return "Product Deleted Successfully ";
	}

	

}
