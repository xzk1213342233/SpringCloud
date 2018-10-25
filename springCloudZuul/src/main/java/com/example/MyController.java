package com.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@RestController
public class MyController {

	@RequestMapping("/local/one")
	public String xzkLocal() {
		
		return "xzkLocal本地跳转";
	}	
}
