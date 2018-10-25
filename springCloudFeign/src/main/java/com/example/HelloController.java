package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController implements MyHelloService{

	@Autowired
	private HelloService helloService;
	
	@RequestMapping(value = "/hello")
	public String sayHello(String name){
		System.out.println("我是一个frign");
	    return helloService.sayHello(name);
	}

	@RequestMapping("/hello1")
	public User hello1(String name,Integer age) {
		return helloService.hello1(name, age);
	}
	
	@RequestMapping("/hello2")
	public String hello2(String name,Integer age) {		
		return helloService.hello2(new User(name, age));
	}
	
	//继承特性  调用接口。减少复制量
	@Override
	@RequestMapping(value = "/myhello")
	public String myHello(String name) {
		// TODO Auto-generated method stub
		return helloService.myHello(name);
	}

	@Override
	@RequestMapping(value = "/apihello")
	public String apiHello(String name) {
		// TODO Auto-generated method stub
		return helloService.apiHello(name);
	}
}
