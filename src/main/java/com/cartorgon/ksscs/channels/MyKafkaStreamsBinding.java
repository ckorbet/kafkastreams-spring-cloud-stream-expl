package com.cartorgon.ksscs.channels;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MyKafkaStreamsBinding {
	
	public static final String KS_EVENT_OUTPUT_NAME = "eventoutput";
	public static final String KS_EVENT_INPUT_NAME = "eventinput";
	
	@Output(KS_EVENT_OUTPUT_NAME)
	MessageChannel eventOutput();
	
	@Output(KS_EVENT_INPUT_NAME)
	MessageChannel eventInput();
}
