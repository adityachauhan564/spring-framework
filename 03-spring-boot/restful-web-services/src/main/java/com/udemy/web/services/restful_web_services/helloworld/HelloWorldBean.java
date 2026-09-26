package com.udemy.web.services.restful_web_services.helloworld;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String message) {
		this.message=message;
		
	}
	//to string 
	
	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

	//getter setter 
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	

}
