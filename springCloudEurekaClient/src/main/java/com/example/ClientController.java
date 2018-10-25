package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController implements MyHelloService{
	
	public static Boolean flag = true;
	
	@Value("${server.port}")
	private String port;
	
	
	@RequestMapping("/hello")
	public String hello(String name) {
		System.out.println("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
		return "hello "+name+",i am from port:" +port;
	}
	
	@RequestMapping("/hello1")
	public User hello1(@RequestHeader String name,@RequestHeader Integer age) {
		return new User(name, age);
	}
	
	@RequestMapping("/hello2")
	public String hello2(@RequestBody User user) {
		return "hello "+user.getName()+"," +user.getAge();
	}


	
	@RequestMapping(value = "/flag/{data}", method = RequestMethod.GET)
	public void LinkDb(@PathVariable Boolean data){
		flag = data;
	}


	@Override
	public String myHello(String name) {
		// TODO Auto-generated method stub
		int sleepTime = new Random().nextInt(5000);
		System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date())+",sleepTime:"+sleepTime);	
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MyHello "+name+",i am from port:" +port;
	}

	@Override
	public String apiHello(String name) {
		// TODO Auto-generated method stub
		return "apiHello,"+name;
	}
	
}
