package com.cartorgon.ksscs.channels;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import com.cartorgon.ksscs.model.MyKafkaStreamsEvent;

public interface MyKafkaStreamsBinding {
	
	public static final String KS_EVENT_OUTPUT_NAME = "eventoutput";
	public static final String KS_STREAM_INPUT_NAME = "streaminput";
	public static final String KS_STREAM_OUTPUT_NAME = "streamoutput";
	public static final String KS_STREAM_COUNT_NAME = "streamcount";
	
	@Output(KS_EVENT_OUTPUT_NAME)
	MessageChannel eventOutput();
	
	@Input(KS_STREAM_INPUT_NAME)
	KStream<String, MyKafkaStreamsEvent> streamInput();
	
	@Output(KS_STREAM_OUTPUT_NAME)
	KStream<String, Long> streamOutput();	
}