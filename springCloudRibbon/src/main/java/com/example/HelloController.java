package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	private HelloService helloService;
	
	@RequestMapping(value = "/hello")
	public String sayHello(String name){
		System.out.println("i am ribbon");
	    return helloService.sayHello(name);
	}
}
