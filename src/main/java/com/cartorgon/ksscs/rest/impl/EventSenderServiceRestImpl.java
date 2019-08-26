package com.cartorgon.ksscs.rest.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cartorgon.ksscs.model.impl.MyKafkaStreamsEventMsg;
import com.cartorgon.ksscs.publication.KafkaPublisherService;
import com.cartorgon.ksscs.rest.EventSenderServiceRest;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class EventSenderServiceRestImpl implements EventSenderServiceRest {
	
	@Autowired
	private KafkaPublisherService publisher;
		
	@PostMapping(path = "/sendevent")
	@Override
	public ResponseEntity<String> sendEvent(
			@Valid @RequestParam(value = "firstName", required = true) String firstName, 
			@Valid @RequestParam(value = "lastName", required = true) String lastName) {
		
		if(StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
			throw new IllegalArgumentException("'firstName' and 'lastName' cannot be null or empty");
		}
		
		log.info("Received REST request to 'sendEvent'...");
		this.publisher.publish(new MyKafkaStreamsEventMsg(firstName, lastName));
		log.info("Serving REST response");
		return new ResponseEntity<>("{\"sent\":\"OK\"}", HttpStatus.OK) ;
	}

	@PostMapping(path = "/dostreaming")
	@Override
	public ResponseEntity<String> doStream() {
		log.info("Received REST request to 'doStreaming'...");
		this.publisher.doStreaming();		
		log.info("Serving REST response");
		return new ResponseEntity<>("{\"sent\":\"OK\"}", HttpStatus.OK) ;
	}
}