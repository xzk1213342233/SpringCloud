package com.example;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {
	
	String MYINPUT = "myInput";

	@Input(MySink.MYINPUT)
	SubscribableChannel input();

}
