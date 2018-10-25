package com.example;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SendToInput {

	String SENDTOINPUT = "sendToInput";

	@Input(SendToInput.SENDTOINPUT)
	SubscribableChannel input();
}
