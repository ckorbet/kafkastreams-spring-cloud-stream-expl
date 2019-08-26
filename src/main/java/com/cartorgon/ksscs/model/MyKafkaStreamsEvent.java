package com.cartorgon.ksscs.model;

public interface MyKafkaStreamsEvent {	
	String getFirstName();
	void setFirstName(String firstName);
	String getLastName();
	void setLastName(String lastName);
}