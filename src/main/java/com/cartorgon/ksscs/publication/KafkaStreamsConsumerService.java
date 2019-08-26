package com.cartorgon.ksscs.publication;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

import com.cartorgon.ksscs.channels.MyKafkaStreamsBinding;
import com.cartorgon.ksscs.model.impl.MyKafkaStreamsEventMsg;

/**
 * <p>
 * Service that consumes/processes events from Kafka broker using Spring Cloud Stream + Kafka Streams
 * </p>
 */
public interface KafkaStreamsConsumerService {
	KStream<String, Long> process(KStream<String, MyKafkaStreamsEventMsg> events);
}
