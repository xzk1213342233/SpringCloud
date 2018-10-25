package com.example;

import org.springframework.stereotype.Component;

/**
 *  feign 服务降级。
  *     需要添加hystrix jar文件，
  *     需要在配置文件中添加feign.hystrix.enabled=true
 *
 */

@Component
public class HelloServiceFallback implements HelloService{

	@Override
	public String myHello(String name) {
		// TODO Auto-generated method stub
		return "myHello服务降级,名称为"+name;
	}

	@Override
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return "sayHello服务降级,名称为"+name;
	}

	@Override
	public User hello1(String name, Integer age) {
		// TODO Auto-generated method stub
		return new User(name, age);
	}

	@Override
	public String hello2(User user) {
		// TODO Auto-generated method stub
		return "hello1服务降级,名称为"+user.getName();
	}

	@Override
	public String apiHello(String name) {
		// TODO Auto-generated method stub
		return "apihello服务降级,名称为"+name;
	}

}
