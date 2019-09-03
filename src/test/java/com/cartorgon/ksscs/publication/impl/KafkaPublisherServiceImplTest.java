package com.cartorgon.ksscs.publication.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.cartorgon.ksscs.KafkastreamsSpringCloudStreamExplApplication;
import com.cartorgon.ksscs.model.MyKafkaStreamsEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Test class for {@link KafkaPublisherServiceImpl}
 * </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { KafkastreamsSpringCloudStreamExplApplication.class })
@EmbeddedKafka
@Slf4j
@DirtiesContext
public class KafkaPublisherServiceImplTest {

	private static String SENDER_TOPIC = "sender.t";

	@Autowired
	private EmbeddedKafkaBroker broker;

	@ClassRule
	public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, true, SENDER_TOPIC);

	private KafkaMessageListenerContainer<String, MyKafkaStreamsEvent> container;

	private BlockingQueue<ConsumerRecord<String, MyKafkaStreamsEvent>> records;

	private void checkPreConditions() {
		assertThat(broker).isNotNull();
		assertThat(embeddedKafka).isNotNull();
	}

	private void setUpConsumer() {
		// set up the Kafka consumer properties
		Map<String, Object> consumerProperties = 
				KafkaTestUtils.consumerProps("sender", "false", embeddedKafka.getEmbeddedKafka());

		// create a Kafka consumer factory
		DefaultKafkaConsumerFactory<String, MyKafkaStreamsEvent> consumerFactory = 
				new DefaultKafkaConsumerFactory<String, MyKafkaStreamsEvent>(consumerProperties);

		// set the topic that needs to be consumed
		ContainerProperties containerProperties = new ContainerProperties(SENDER_TOPIC);

		// create a Kafka MessageListenerContainer
		container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);

		// create a thread safe queue to store the received message
		records = new LinkedBlockingQueue<>();

		// setup a Kafka message listener
		container.setupMessageListener(new MessageListener<String, MyKafkaStreamsEvent>() {
			@Override
			public void onMessage(ConsumerRecord<String, MyKafkaStreamsEvent> record) {
				log.debug("test-listener received message='{}'", record.toString());
				records.add(record);
			}
		});

		// start the container and underlying message listener
		container.start();

		// wait until the container has the required number of assigned partitions
		ContainerTestUtils.waitForAssignment(container, embeddedKafka.getEmbeddedKafka().getPartitionsPerTopic());
	}

	@Before
	public final void init() {
		this.checkPreConditions();
		this.setUpConsumer();
		assertThat(this.container.isRunning()).isTrue();
	}

	@Test
	public final void testPublish() {
		throw new UnsupportedOperationException("Test not yest implemented");
		// TODO
	}
}