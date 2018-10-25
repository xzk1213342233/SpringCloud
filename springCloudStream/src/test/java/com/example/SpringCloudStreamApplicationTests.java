package com.example;

import java.text.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCloudStreamApplicationTests {
	
    /**
     * 	bean的名称要和通道一一对应，input对应Sink.INPUT，
         *   你也可以将名称改成myInput，对应自定义的MySink.MYINPUT，然后在SinkReceiver中绑定
     */	
	@Autowired
	private  MessageChannel input;
	@Autowired
	private  MessageChannel myInput;
	@Autowired
	private  MessageChannel sendToInput;
	
	@Test
	public void contextLoads() throws ParseException {
//		input.send(MessageBuilder.withPayload(new User("小明", 18)).build());
//		myInput.send(MessageBuilder.withPayload("hello world myInput").build());
//		sendToInput.send(MessageBuilder.withPayload("hello world sendToInput").build());
		
		
		//消息分区

	}

}
