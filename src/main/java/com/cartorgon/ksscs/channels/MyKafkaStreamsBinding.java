package com.cartorgon.ksscs.channels;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import com.cartorgon.ksscs.model.MyKafkaStreamsEvent;

public interface MyKafkaStreamsBinding {
	
	public static final String KS_EVENT_OUTPUT_NAME = "eventOutput";
	public static final String KS_STREAM_INPUT_NAME = "streamInput";
	public static final String KS_STREAM_OUTPUT_NAME = "streamOutput";
	public static final String KS_STREAM_COUNT_NAME = "streamCount";
	
	@Output(KS_EVENT_OUTPUT_NAME)
	MessageChannel eventOutput();
	
	@Input(KS_STREAM_INPUT_NAME)
	KStream<String, MyKafkaStreamsEvent> streamInput();
	
	@Output(KS_STREAM_OUTPUT_NAME)
	KStream<String, Long> streamOutput();	
}