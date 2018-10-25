package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

@Service
public class RibbonHelloService {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * fallbackMethod   指定失败回调函数，服务降级
	 * ignoreExceptions 忽略指定异常，此异常下不会调用失败回调函数（fallbackMethod），不会触发服务降级
	 * 
	 * @CacheResult 设置请求缓存，开启缓存 功能。配合@HystrixCommand使用，
	 * 定义缓存key：
	 *    1、使用cacheKeyMethod方法指定具体的生成函数， (cacheKeyMethod = "getName")
	 *    2、使用@CacheKey注解，此注解的优先级要比cacheKeyMethod低。 @CacheKey("name")
	 * 
	 * 注意：如果是update这种请求，需要对失效的缓存进行处理，所以我们需要在update的方法上添加
	 *      @CacheRemove注解，且需要指定commandKey参数
	 *      
	 *      例如：@CacheRemove(commandKey = "sayHello")
	 * 
	 * 
	 *  请求合并：减少通信消耗和线程数的占用  @HystrixCollapser
	 *  何时适合使用请求合并。
	 *  1、请求延迟时间大。
	 *  2、请求并发量高。
	 *  timerDelayInMilliseconds 合并时间窗时间
	 * 
	 * 
	 * execution.isolation.thread.timeoutInMilliseconds  HystrixCommand执行的超时时间（毫秒）
	 * 
	 */
	@HystrixCommand(fallbackMethod = "sayHelloError",ignoreExceptions=NullPointerException.class,
			        commandProperties= {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000")})
	@CacheResult(cacheKeyMethod = "getName")
	public String sayHello(String name) {
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if("xzk".equalsIgnoreCase(name)) {
			String str = null;
			str.toString();
		 }
		 if("xzk1213".equalsIgnoreCase(name)) {
			 int i = 1/0;
		 }
	     return restTemplate.getForObject("http://SERVICE-HELLO/hello?name="+name,String.class);
	}
	
	/**
	 * 如果需要对异常进行处理，需要在方法中增加Throwable对象
	 */
	public String sayHelloError(String name,Throwable e) {
	     return "hello,"+name+",sorry,error!             "+e.getMessage();
	}
	
	/**
	 * 定义缓存key的方法
	 */
	private String getName(String name) {
		return name;
	}
	

	//自定义Command
	public String myHello(String name) {
		MyCommand myCommand = new MyCommand(restTemplate, name);
	    //同步执行
		return myCommand.execute();
	    //异步执行
//		Future<String>  future = myCommand.queue();
//		return future.get();
	}
	
	@HystrixCollapser(batchMethod = "findAll",collapserProperties = {@HystrixProperty(name ="timerDelayInMilliseconds",value = "100")})
	public Future<String> find(String name) {
	    return null;
	}
	@HystrixCommand
	public List<String> findAll(List<String> names) {
	    System.out.println("我是findAll"+names);
	    return names;
	}
}
