package com.springcore.standalone.collections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		
		ApplicationContext con=new ClassPathXmlApplicationContext("com/springcore/standalone/collections/aloneconfig.xml"); // this is the path so slash will be used 
		
		Person person1=(Person)con.getBean("person1" ,Person.class);
		//or Person person1=(Person)con.getBean("person1");
		
		
		System.out.println(person1);
		System.out.println(person1.getFriends().getClass().getName());
		System.out.println("************************ Map is here *********************************");
		System.out.println(person1.getFeestructure());
		System.out.println("________________________Properties is here_______________");
		System.out.println(person1.getProperties());
	}

}
