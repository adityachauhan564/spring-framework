package com.springcore.lifecycle;

public class Sarbat {
	
	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		System.out.println("Setting Price");
		this.price = price;
	}

	public Sarbat() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Sarbat [price=" + price + "]";
	}

	public void hey() {
		
		System.out.println("Inside init Method: Hey, How are you");
		
		
	}
	
	public void bye() {
		
		System.out.println("Inside the Destroy Method: I am going to somewhere else");
	}
	
	
	
	

}
