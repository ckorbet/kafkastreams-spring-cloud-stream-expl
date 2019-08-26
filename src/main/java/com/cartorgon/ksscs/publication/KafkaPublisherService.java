package com.cartorgon.ksscs.publication;

import com.cartorgon.ksscs.model.MyKafkaStreamsEvent;

/**
 * <p>
 * Service that publishes events to kafka using <i>'plain'</i> Spring Cloud Stream
 * </p>
 */
public interface KafkaPublisherService {	
	void publish(MyKafkaStreamsEvent event);
	void doStreaming();
}