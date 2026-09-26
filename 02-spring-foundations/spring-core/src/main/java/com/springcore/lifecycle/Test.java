package com.springcore.lifecycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	
	
	public static void main(String[] args) {
		
	  AbstractApplicationContext context =new ClassPathXmlApplicationContext("com/springcore/lifecycle/lifecycle_config.xml");
	  //registering shutdown hook 
	  context.registerShutdownHook();
	/*  
	  Sarbat s1=(Sarbat) context.getBean("s1");
	  
	  System.out.println(s1);
	 
	  context.registerShutdownHook();
	  
	  System.out.println("+++++++++++++++++++++++++++++++++");
	  
	  Mattha m1=(Mattha) context.getBean("m1");
	  
	  */
	  Using_Annotation_example example=(Using_Annotation_example) context.getBean("example");
	  
	  System.out.println("****************************************************************");
	  System.out.println(example);
		
	}

}
