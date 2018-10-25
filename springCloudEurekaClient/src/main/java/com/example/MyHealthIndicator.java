package com.example;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * 自定义健康指示器(Spring Boot的内容)
 * 
 * 这里修改了服务的状态，也是针对于service-hello自身来说的，我们能够通过/actuator/health端点知道服务是否是正常的
 * eureka-server并不知道，状态依然是UP
 * @author xzk
 *
 */
@Component
public class MyHealthIndicator implements HealthIndicator{

	@Override
	public Health health() {
		if(ClientController.flag){
			return new Health.Builder(Status.UP).build();
		}else{
			return new Health.Builder(Status.DOWN).build();
		}

	}
}
