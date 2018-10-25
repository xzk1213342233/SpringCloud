package com.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

@RestController
public class HelloController {

	@Autowired
	private RibbonHelloService ribbonHelloService;
	
	@Autowired
	private FeignHelloService feignHelloService;
	
	
	@RequestMapping(value = "/hello")
	public String sayHello(String name){
		//初始化Hystrix请求上下文
		HystrixRequestContext.initializeContext();
		String str1 = ribbonHelloService.sayHello(name);
		String str2 = ribbonHelloService.sayHello(name);
		String str3 = ribbonHelloService.sayHello(name);
		System.out.println("hello缓存参数测试str1="+str1+",str2="+str2+",str3"+str3);
	    return ribbonHelloService.sayHello(name);
	}
	
	@RequestMapping(value = "/myhello")
	public String myhello(String name){
		String str1 = ribbonHelloService.myHello(name);
		String str2 = ribbonHelloService.myHello(name);
		String str3 = ribbonHelloService.myHello(name);
		System.out.println("myHello缓存参数测试str1="+str1+",str2="+str2+",str3"+str3);
	    return ribbonHelloService.myHello(name);
	}
	
	@RequestMapping(value = "/collapser")
	public void collapser(String name) throws InterruptedException, ExecutionException{
		//初始化Hystrix请求上下文
	    HystrixRequestContext context = HystrixRequestContext.initializeContext();
	    Future<String> f1 = ribbonHelloService.find(name+"1");
	    Future<String> f2 = ribbonHelloService.find(name+"2");
	    Future<String> f3 = ribbonHelloService.find(name+"3");
	    String b1 = f1.get();
	    String b2 = f2.get();
	    String b3 = f3.get();
	    Thread.sleep(3000);
	    Future<String> f4 = ribbonHelloService.find(name+"4");
	    String b4 = f4.get();
	    System.out.println("b1>>>"+b1);
	    System.out.println("b2>>>"+b2);
	    System.out.println("b3>>>"+b3);
	    System.out.println("b4>>>"+b4);
	    context.close();
	}
}
