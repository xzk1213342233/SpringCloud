package com.example;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * 
  *  用于接收来自RabbitMq消息的消费者SinkReceiver
 *  
 *  @EnableBinding 该注解用来指定一个或多个定义了@input或者@output注解的接口，以此实现对消息通道（channel）的绑定，
 *  @EnableBinding(Sink.class) 绑定了Sink接口，该接口是SpringCloudStream中默认实现的对输入消息通道绑定的定义。
 *  
 *  Sink通过@input注解绑定了一个名为input的消息通道，除了sink以外，springCloudStream还默认实现了绑定output通道的
 *  Source接口，还有结合了Sink和Source的Processor接口，实际使用时候我们也可以自己通过@input和@output注解来定义绑定
  *    消息通道的接口。
 *    
 *  @StreamListener 定义在方法上，将被修饰的方法注册为消息中间件上数据流的事件监听器，注解中的属性值对应了监听器的消息通道名。
 *    
 *  
 *
 */
@EnableBinding(value= {MySink.class,Sink.class,SinkSender.class})
public class SinkReceiver {
	
	@StreamListener(Sink.INPUT)
	public void receiveInput(Object payload) {
		System.out.println("Receive-input:"+payload);
	}

	@StreamListener(MySink.MYINPUT)
	public void receiveMyInput(Object payload) {
		System.out.println("Receive-myInput:"+payload);
	}
	
	//消息分区
    @StreamListener(Sink.INPUT)
    public void listen(@Payload Object payload) {
        System.out.println("消费分区-received from payload " + payload );
    }
}
