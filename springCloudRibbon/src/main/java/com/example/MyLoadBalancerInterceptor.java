package com.example;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class MyLoadBalancerInterceptor implements ClientHttpRequestInterceptor{

	 
		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body,
				ClientHttpRequestExecution execute) throws IOException {
			
			System.out.println("自定义拦截器");
			System.out.println("请求的URI地址是："+request.getURI());
			HttpRequest myHttpRequest = new MyLoadBalancerRequest(request);
			
			return execute.execute(myHttpRequest, body);
		}
}
