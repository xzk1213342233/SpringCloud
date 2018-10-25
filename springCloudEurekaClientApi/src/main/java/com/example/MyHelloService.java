package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface MyHelloService {
	
	@RequestMapping(value = "/myhello",method = RequestMethod.GET)
	String myHello(@RequestParam(value = "name") String name);
	
	@RequestMapping(value = "/apihello",method = RequestMethod.GET)
	String apiHello(@RequestParam(value = "name") String name);
}
