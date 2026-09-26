package com.springcore.auto.wire.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		
		ApplicationContext context=new ClassPathXmlApplicationContext("com/springcore/auto/wire/annotation/autoconfig.xml");
		
		
		Emp emp1=context.getBean("emp1", Emp.class); //by using Emp.class here  , you don't need further to do type Cast
		// TODO Auto-generated method stub

		System.out.println(emp1);
	}

}
