package com.example;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

	@Autowired
	private RestTemplate restTemplate;
	
	public String sayHello(String name) {	
		
		//GET请求(POST一样)----->还有PUT请求和DELETDE请求
		
		//使用getForEntity方式
		//第一种 参数拼接
//		ResponseEntity<String> re1 =restTemplate.getForEntity("http://SERVICE-HELLO/hello?name="+name, String.class);
//		System.out.println("getForEntity数据1"+re1.getBody());
		//第二种 参数占位符
//		ResponseEntity<String> re2 =restTemplate.getForEntity("http://SERVICE-HELLO/hello?name={1}", String.class,name);
//		System.out.println("getForEntity数据2"+re2.getBody());
		//第二种 Map参数
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("myName", name);
//		ResponseEntity<String> re3 =restTemplate.getForEntity("http://SERVICE-HELLO/hello?name={myName}", String.class,map);
//		System.out.println("getForEntity数据3"+re3.getBody());
		
		
	    //使用getForObject方式(其实就是对getForEntity的进一步封装)
		return restTemplate.getForObject("http://SERVICE-HELLO/hello?name="+name,String.class);
	}
}
