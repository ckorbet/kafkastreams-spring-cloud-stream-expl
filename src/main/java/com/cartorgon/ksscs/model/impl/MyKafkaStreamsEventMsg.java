package com.cartorgon.ksscs.model.impl;

import java.io.Serializable;

import com.cartorgon.ksscs.model.MyKafkaStreamsEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyKafkaStreamsEventMsg implements MyKafkaStreamsEvent, Serializable {

	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
}