package com.stock.flex.resource.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.util.List;

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

	public HttpStatus getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}


}
