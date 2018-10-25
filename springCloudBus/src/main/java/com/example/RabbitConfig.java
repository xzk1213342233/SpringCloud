package com.example;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//此类可以用来配置队列、交换器、路由等高级信息
@Configuration
public class RabbitConfig {

	//配置一个简单的队列
	@Bean
	public Queue helloQueue() {
		Queue myQueue = new Queue("hello");
		return myQueue;
	}
	
}
