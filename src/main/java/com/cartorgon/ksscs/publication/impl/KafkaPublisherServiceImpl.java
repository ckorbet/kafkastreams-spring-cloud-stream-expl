package com.cartorgon.ksscs.publication.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import com.cartorgon.ksscs.channels.MyKafkaStreamsBinding;
import com.cartorgon.ksscs.model.MyKafkaStreamsEvent;
import com.cartorgon.ksscs.model.impl.MyKafkaStreamsEventMsg;
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
	
	private static final Random RANDOM = new Random();
	
	@Autowired
	private MessageChannel eventOutput;

	@Override
	public final void publish(final MyKafkaStreamsEvent event) {		
		final Message<MyKafkaStreamsEvent> msg = MessageBuilder
				.withPayload(event)
				.setHeader(KafkaHeaders.MESSAGE_KEY, event.getFirstName().getBytes())
				.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		try {
			log.info(String.format("Publishing [%s] ...", event.toString()));
			this.eventOutput.send(msg);
			log.info("Publication completed !!");
		} catch (final Exception excp) {
			log.error("Publication failed !!", excp);
		}		
	}

	@Override
	public final void doStreaming() {
		final List<MyKafkaStreamsEvent> events = Arrays.asList(
				new MyKafkaStreamsEventMsg("Carlos", "Torres"),
				new MyKafkaStreamsEventMsg("Dimitri", "Koklov"),
				new MyKafkaStreamsEventMsg("Piros", "Dimas"),
				new MyKafkaStreamsEventMsg("Lu", "Xioajun"),
				new MyKafkaStreamsEventMsg("Ilya", "Ilyin")
				);		
				
		final Runnable runnable = () -> {
			final MyKafkaStreamsEvent event = events.get(RANDOM.nextInt(events.size()));
			try {
				log.info(String.format("Stream publication [%s] ...", event.toString()));
				this.eventOutput.send(MessageBuilder					
						.withPayload(event)
						.setHeader(KafkaHeaders.MESSAGE_KEY, event.getFirstName().getBytes())
						.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.build());
			} catch (final Exception excp) {
				log.error("Stream publication failed !!", excp);
			}
		};		
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);		
	}
}