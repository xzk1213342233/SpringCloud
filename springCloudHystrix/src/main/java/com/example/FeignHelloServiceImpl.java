package com.example;

import org.springframework.stereotype.Component;

@Component
public class FeignHelloServiceImpl implements FeignHelloService{

	@Override
	public String sayHello(String name) {
		return "feign-hello,"+name+",sorry,error!";
	}

}
