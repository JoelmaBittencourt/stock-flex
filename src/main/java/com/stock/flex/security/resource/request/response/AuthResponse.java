package com.stock.flex.security.resource.request.response;

import lombok.Getter;

@Getter
public class AuthResponse {
	
	private String token;

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

}
