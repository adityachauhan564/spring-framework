package com.bootcamp.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //spring automatically will mark callname as Table name
public class Product { 
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer pId; //
	private String pName;
	private Double pPrice;
	private Integer pQuantity;
	public Integer getPId() {
		return pId;
	}
	public void setPId(Integer pId) {
		this.pId = pId;
	}
	public String getPName() {
		return pName;
	}
	public void setPName(String pName) {
		this.pName = pName;
	}
	public Double getPPrice() {
		return pPrice;
	}
	public void setPPrice(Double pPrice) {
		this.pPrice = pPrice;
	}
	public Integer getPQuantity() {
		return pQuantity;
	}
	public void setPQuantity(Integer pQuantity) {
		this.pQuantity = pQuantity;
	}
	public Product(Integer pId, String pName, Double pPrice, Integer pQuantity) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pQuantity = pQuantity;
	}
	public Product() {
		super();
		
	}
	
	
	

}
