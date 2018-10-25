package com.example;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SendToOutput {

	 String SENDTOOUTPUT = "sendToOutput";
	
	 @Output(SendToOutput.SENDTOOUTPUT)
	 MessageChannel output();
}
