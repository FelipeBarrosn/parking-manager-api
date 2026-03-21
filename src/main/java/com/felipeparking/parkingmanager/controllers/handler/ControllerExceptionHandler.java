package com.felipeparking.parkingmanager.controllers.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String userMessage = e.getBindingResult().getFieldError().getDefaultMessage();
		StandardError err = new StandardError(Instant.now(), status.value(), userMessage, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> validationGlobal(Exception e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String userMessage = e.getMessage();
		StandardError err = new StandardError(Instant.now(), status.value(), userMessage, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
