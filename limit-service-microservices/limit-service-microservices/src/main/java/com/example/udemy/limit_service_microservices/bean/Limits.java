package com.example.udemy.limit_service_microservices.bean;

public class Limits {

	private int minimum;
	private int maximum;// Java Best practices is using private here *

	public Limits(int minimum, int maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}

	// No argument constructor
	public Limits() {
		super();

	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

}
