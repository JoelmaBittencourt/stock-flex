package com.stock.flex.exception;

public class DuplicationEmailException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicationEmailException(String message) {
		super(message);
	}
	
}
