package com.example;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


@EnableBinding(value= {SendToOutput.class})
public class SendToSender {

	@StreamListener(SendToOutput.SENDTOOUTPUT)
	public void receiveOutput(Object payload) {
		System.out.println("SendToSender-output:"+payload);
	}
}
