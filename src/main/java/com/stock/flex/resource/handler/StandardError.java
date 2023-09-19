package com.stock.flex.resource.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StandardError {
	private HttpStatus status;
	private String error;
	private String message;
	private String path;

	public StandardError(HttpStatus status, Throwable ex, HttpServletRequest request) {
		this.status = status;
		this.error = ex.getClass().getSimpleName();
		this.message = ex.getMessage();
		this.path = request.getRequestURI();
	}

}
