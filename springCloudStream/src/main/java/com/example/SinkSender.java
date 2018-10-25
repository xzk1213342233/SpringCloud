package com.example;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;



public interface SinkSender {
	
	@Output(Source.OUTPUT)
	MessageChannel output();

}
