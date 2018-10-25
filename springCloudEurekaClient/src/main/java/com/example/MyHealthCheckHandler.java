package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

/**
 * 
 * 健康检查处理器
 * 
 * 把/actuator/health端点返回的服务状态告诉eureka-server
 * 在服务列表里面把服务的状态更改到“DOWN”状态，不要继续让请求涌入这个服务
 * 
 * @author Administrator
 *
 */
@Component
public class MyHealthCheckHandler implements HealthCheckHandler {
	
	@Autowired
	private MyHealthIndicator myHealthIndicator;

	@Override
	public InstanceStatus getStatus(InstanceStatus currentStatus) {
		// TODO Auto-generated method stub
		Status status = myHealthIndicator.health().getStatus();
		System.out.println(status);
		if(Status.UP.equals(status)) {
			return InstanceStatus.UP;
		}else {
			return InstanceStatus.DOWN;
		}
	}

}
