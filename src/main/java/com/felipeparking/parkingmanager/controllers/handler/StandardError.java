package com.felipeparking.parkingmanager.controllers.handler;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
public class StandardError {

	private Instant timestamp;
	private Integer status;
	private String message;
	private String path;
	
}
