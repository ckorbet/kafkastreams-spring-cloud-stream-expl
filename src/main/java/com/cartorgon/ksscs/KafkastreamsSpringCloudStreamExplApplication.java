package com.cartorgon.ksscs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import com.cartorgon.ksscs.channels.MyKafkaStreamsBinding;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableBinding(MyKafkaStreamsBinding.class)
@Slf4j
public class KafkastreamsSpringCloudStreamExplApplication {
	
	@Autowired
	private ScheduledExecutorService executorService;
	
	public static final void main(final String[] args) {
		SpringApplication.run(KafkastreamsSpringCloudStreamExplApplication.class, args);
	}
	
	@Bean
	public ScheduledExecutorService executorService() {
		return Executors.newScheduledThreadPool(1);
	}
	
	@PreDestroy
	public final void preDestroy() {
		log.info("Shutting down event publication thread...");
		this.executorService.shutdownNow();		
		log.info("Event publication thread stopped");
	}	
}