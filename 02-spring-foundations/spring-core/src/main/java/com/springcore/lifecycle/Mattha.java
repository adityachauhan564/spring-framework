package com.springcore.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Mattha implements InitializingBean, DisposableBean {
	
	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		//init
		
		System.out.println("Taking Mattha : init");
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		//destroy
		System.out.println("Going to put Glass back to the Sank : destroy ");
		
	}

	
	
}
