package com.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@RestController
public class MyErrorController implements ErrorController {

	
	@RequestMapping("/error")
	public String errorOne(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("咔咔咔咔咔咔扩扩扩扩扩扩扩扩扩扩扩扩扩");
		String status_code = request.getAttribute("javax.servlet.error.status_code").toString();
		String exceptionStr = request.getAttribute("javax.servlet.error.exception").toString();
		System.out.println(status_code);
		System.out.println(exceptionStr);
		RequestContext ctx = RequestContext.getCurrentContext();
		ZuulException exception = (ZuulException)ctx.getThrowable();
		int nStatusCode = exception.nStatusCode;
		String message = exception.getMessage();
		return "nStatusCode="+nStatusCode+",message="+message;
	}
	
	@RequestMapping("/xzkerror")
	public String xzkerror(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("xzkerror");
		String status_code = request.getAttribute("javax.servlet.error.status_code").toString();
		String exceptionStr = request.getAttribute("javax.servlet.error.exception").toString();
		System.out.println(status_code);
		System.out.println(exceptionStr);
		RequestContext ctx = RequestContext.getCurrentContext();
		ZuulException exception = (ZuulException)ctx.getThrowable();
		int nStatusCode = exception.nStatusCode;
		String message = exception.getMessage();
		return "xzkerror======nStatusCode="+nStatusCode+",message="+message;
	}
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

}
