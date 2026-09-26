package com.udemy.web.services.restful_web_services.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// REST API
@RestController
public class HelloWorldController {
	// hello-world
	
@GetMapping("/hello-world")
public String helloWorld() {
		
		return "Hello-World";
	}

@GetMapping("/hello-world-bean")
public HelloWorldBean helloWorldBean() {
	
	return new HelloWorldBean ("Hello-World");
}

//Path Parameter
// /users/{/id}/todos/{id} => /users/2/todos/200
//hello-world/path-variable/{name}
// /hello-world/path-variable/Adi

@GetMapping(path="/hello-world/path-variable/{name}")
public HelloWorldBean helloworldPathVariable(@PathVariable String name) {
	
	return new HelloWorldBean(String.format("Hello-World, %s", name));
}
}
