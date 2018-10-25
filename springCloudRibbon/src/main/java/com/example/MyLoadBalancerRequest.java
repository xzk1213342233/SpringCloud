package com.example;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;

public class MyLoadBalancerRequest implements HttpRequest {

    HttpRequest sourceRequest;
    
    public MyLoadBalancerRequest(HttpRequest sourceRequest) {
    	this.sourceRequest = sourceRequest;
    }
    
	@Override
	public HttpHeaders getHeaders() {
		// TODO Auto-generated method stub
		return sourceRequest.getHeaders();
	}

	@Override
	public String getMethodValue() {
		// TODO Auto-generated method stub
		return sourceRequest.getMethodValue();
	}

	@Override
	public URI getURI() {
		// TODO Auto-generated method stub
		try {
			URI myUri = new URI("http://localhost:8188/hello");
			return myUri; 
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sourceRequest.getURI();
	}

}
