package com.cartorgon.ksscs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.cartorgon.ksscs.channels.MyKafkaStreamsBinding;

@SpringBootApplication
@EnableBinding(MyKafkaStreamsBinding.class)
public class KafkastreamsSpringCloudStreamExplApplication {
	public static final void main(final String[] args) {
		SpringApplication.run(KafkastreamsSpringCloudStreamExplApplication.class, args);
	}
}