package com.stock.flex.resource.request;

public record AuthResponse(String token) {
	

	public AuthResponse(String token) {
		this.token = token;
	}


}
