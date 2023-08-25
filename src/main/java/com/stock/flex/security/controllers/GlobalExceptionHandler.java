package com.stock.flex.security.controllers;

import com.stock.flex.security.dtos.StandardErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardErrorDTO> exception(Exception ex, HttpServletRequest request) {
		return ResponseEntity.badRequest().body(
				new StandardErrorDTO(HttpStatus.BAD_REQUEST, ex, request));
	}

}
