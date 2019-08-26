package com.cartorgon.ksscs.rest;

import org.springframework.http.ResponseEntity;

public interface EventSenderServiceRest {	
	ResponseEntity<String> sendEvent(String firstName, String lastName);
	ResponseEntity<String> doStream();	
}
