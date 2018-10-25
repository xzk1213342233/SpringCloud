package com.example;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(value= {SendToInput.class,SendToOutput.class})
public class SendToReceiver {
	
	@StreamListener(SendToInput.SENDTOINPUT)
	@SendTo(SendToOutput.SENDTOOUTPUT)
	public Object receiveInput(Object payload) {
		System.out.println("SendToReceiver-input:"+payload);
		return "SendToReceiver return"+payload;
	}

}
