package com.stock.flex.resource;

import com.stock.flex.resource.request.AuthRequest;
import com.stock.flex.resource.response.AuthResponse;
import com.stock.flex.resource.request.RegisterRequest;
import com.stock.flex.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest dto) {
		return ResponseEntity.ok(authService.register(dto));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest dto) {
		return ResponseEntity.ok(authService.authenticate(dto));
	}
}
