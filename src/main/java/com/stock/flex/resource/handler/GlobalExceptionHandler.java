package com.stock.flex.resource.handler;

import com.stock.flex.resource.handler.request.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> exception(Exception ex, HttpServletRequest request) {
		return ResponseEntity.badRequest().body(
				new StandardError(HttpStatus.BAD_REQUEST, ex, request));
	}

}
