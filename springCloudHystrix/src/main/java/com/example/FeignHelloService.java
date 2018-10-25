package com.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="SERVICE-HELLO",fallback = FeignHelloServiceImpl.class)
public interface FeignHelloService {
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	String sayHello(@RequestParam(value = "name") String name);
}
