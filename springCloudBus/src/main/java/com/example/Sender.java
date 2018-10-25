package com.example;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *   消息生产者
 *   产生一个字符串，发送到名为hello的队列中
 */
@Component
public class Sender {

	//AmqpTemplate接口定义了一套针对AMQP协议的基础操作。在sb中会根据配置来注入具体实现
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send() {
		String context = "hello"+new Date();
		System.out.println("Sender:"+context);
		amqpTemplate.convertAndSend("hello",context);
		
	}
	
}
