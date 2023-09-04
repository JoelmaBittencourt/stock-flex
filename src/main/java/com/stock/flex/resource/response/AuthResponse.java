package com.stock.flex.resource.response;

public record AuthResponse(String token) {
	

	public AuthResponse(String token) {
		this.token = token;
	}


}
