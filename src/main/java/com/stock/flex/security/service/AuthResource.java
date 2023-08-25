package com.stock.flex.security.service;

import com.stock.flex.security.resource.request.response.AuthResponse;
import com.stock.flex.security.resource.request.AuthRequest;
import com.stock.flex.security.resource.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest dto) {
		return ResponseEntity.ok(authService.authenticate(dto));
	}
}
