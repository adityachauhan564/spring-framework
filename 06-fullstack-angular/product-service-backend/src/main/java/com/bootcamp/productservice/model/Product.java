package com.bootcamp.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/*
 * The JSON keys come from the getter names: getName() -> "name".
 * The course named the fields pName / pPrice, so the getters were getPName(), which Jackson turns into
 * "pname" (it makes the leading capital letters small). The Angular model expected "pName" and got nothing.
 * Plain names avoid the whole problem.
 */
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Name can have at most 100 characters")
	@Column(nullable = false, length = 100)
	private String name;

	@NotNull(message = "Price is required")
	@PositiveOrZero(message = "Price can't be negative")
	private Double price;

	@NotNull(message = "Quantity is required")
	@PositiveOrZero(message = "Quantity can't be negative")
	private Integer quantity;

	public Product() {
	}

	public Product(String name, Double price, Integer quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
