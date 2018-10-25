package com.example;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ErrorFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		RequestContext ctx  = RequestContext.getCurrentContext();
		Throwable throwable  = ctx.getThrowable();
		System.out.println("这是一个异常:"+throwable.getCause().getMessage());
		ctx.set("error_status_code",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ctx.set("error.exception",throwable.getCause());
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "error";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 10;
	}

}
