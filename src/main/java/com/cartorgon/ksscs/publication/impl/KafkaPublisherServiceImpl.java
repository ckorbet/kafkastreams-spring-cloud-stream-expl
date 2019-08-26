package com.cartorgon.ksscs.publication.impl;

import org.springframework.http.MediaType;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import com.cartorgon.ksscs.model.MyKafkaStreamsEvent;
import com.cartorgon.ksscs.publication.KafkaPublisherService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Service that publishes events to kafka using <i>'plain'</i> Spring Cloud Stream
 * </p>
 */
@Service
@Slf4j
public class KafkaPublisherServiceImpl implements KafkaPublisherService {
	
	private final MessageChannel eventoutput;
	
	/**
	 * <p>
	 * Default constructor
	 * </p>
	 */
	public KafkaPublisherServiceImpl(final MessageChannel eventoutput) {
		this.eventoutput = eventoutput;
	}

	@Override
	public void publish(MyKafkaStreamsEvent event) {
		
		final Message<MyKafkaStreamsEvent> msg = 
				MessageBuilder
				.withPayload(event)
				.setHeader(KafkaHeaders.MESSAGE_KEY, event.getFirstName().getBytes())
				.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		try {
			log.info(String.format("Publishing [%s] ...", event.toString()));
			this.eventoutput.send(msg);
			log.info("Publication completed !!");
		} catch (final Exception excp) {
			log.error("Publication failed !!", excp);
		}		
	}
}