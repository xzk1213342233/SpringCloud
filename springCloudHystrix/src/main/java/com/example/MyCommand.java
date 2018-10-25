package com.example;

import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;


/**
 * 自定义HystrixCommand 不使用注解形式
 * 
 * @author xzk
 *
 */
public class MyCommand extends HystrixCommand<String>{
	
	private String name;
	private RestTemplate restTemplate;

	protected MyCommand(RestTemplate restTemplate,String name) {

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))//必须参数，默认情况下，同组的命令使用同一线程池
                .andCommandKey(HystrixCommandKey.Factory.asKey("query"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ExampleThreadPool"))//更细粒度的线程池划分
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(20))//服务线程池数量
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withCircuitBreakerErrorThresholdPercentage(60) //熔断器关闭到打开阈值
                                .withCircuitBreakerSleepWindowInMilliseconds(3000)//熔断器打开到关闭的时间窗长度
                        ));
        this.restTemplate =restTemplate;
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		return restTemplate.getForObject("http://SERVICE-HELLO/hello?name="+name,String.class);
	}
	
	@Override
    protected String getFallback() {
        return "MyCommand错误。。。。。";
    }
	

}
