package com.cartorgon.ksscs.publication.impl;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.cartorgon.ksscs.channels.MyKafkaStreamsBinding;
import com.cartorgon.ksscs.model.impl.MyKafkaStreamsEventMsg;
import com.cartorgon.ksscs.publication.KafkaStreamsConsumerService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Service that consumes/processes events from Kafka broker using Spring Cloud Stream + Kafka Streams
 * </p>
 */
@Service
@Slf4j
public class KafkaStreamsConsumerServiceImpl implements KafkaStreamsConsumerService{

	@StreamListener(MyKafkaStreamsBinding.KS_STREAM_INPUT_NAME)
	@SendTo(MyKafkaStreamsBinding.KS_STREAM_OUTPUT_NAME)
	public final KStream<String, Long> process(final KStream<String, MyKafkaStreamsEventMsg> events) {
		/*
		 * Below the example functionality that's done with Kafka Streams
		 */
		return events
		.filter((key, value) -> {
			log.info(String.format("Filtering %s %s", value.getFirstName(), value.getLastName()));
			final boolean res = "Carlos".equals(value.getFirstName());
			if(res) {
				log.info(String.format("'firstName' '%s' found and filtered in", value.getFirstName()));
			}
			return res;
		})
		.map((key, value) -> new KeyValue<>(value.getFirstName() + " " + value.getLastName(), "0"))
		.groupByKey()
		.count(Materialized.as(MyKafkaStreamsBinding.KS_STREAM_COUNT_NAME))
		.toStream()
		;
	}
}