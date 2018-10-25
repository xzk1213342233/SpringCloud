package com.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
  *   服务降级 HelloServiceFallback
  *   日志配置 FullLogConfiguration
 * 
 *
 */
@FeignClient(value="service-hello",fallback=HelloServiceFallback.class,configuration=DisableHystrixConfiguration.class)
public interface HelloService extends MyHelloService {

	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	String sayHello(@RequestParam(value = "name") String name);
	
	@RequestMapping("/hello1")
	public User hello1(@RequestHeader String name,@RequestHeader Integer age);
	
	@RequestMapping("/hello2")
	public String hello2(@RequestBody User user);
}
