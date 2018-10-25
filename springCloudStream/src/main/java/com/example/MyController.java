package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	@Autowired
	private SinkSender sinkSender;

	@RequestMapping("/send")
	public String send(String value) {
		sinkSender.output().send(MessageBuilder.withPayload(value).build());
		return "发送成功";
	}
}
