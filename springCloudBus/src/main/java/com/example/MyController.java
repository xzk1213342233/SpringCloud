package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MyController {

//	@Value("${foo}")
//	private String foo;
	
	@Autowired
	private Environment env;
	
	@RequestMapping("/show")
	public String show() {
		return env.getProperty("foo");
	}
}
