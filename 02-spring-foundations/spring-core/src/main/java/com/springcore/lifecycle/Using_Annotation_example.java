package com.springcore.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Using_Annotation_example {
	
	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Using_Annotation_example() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Using_Annotation_example [subject=" + subject + "]";
	}
	@PostConstruct
	public void start() {
		System.out.println("starting method");
	}
	@PreDestroy
	public void end() {
		System.out.println("ending method");	
		
	}

}
