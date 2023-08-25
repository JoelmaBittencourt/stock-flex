package com.stock.flex.security.service;

import com.stock.flex.security.user.UserSpringSecurityService;
import com.stock.flex.security.user.UserSpringSecurity;
import com.stock.flex.security.resource.request.response.AuthResponse;
import com.stock.flex.security.resource.request.AuthRequest;
import com.stock.flex.security.resource.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private UserSpringSecurityService userSpringSecurityService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthResponse register(RegisterRequest request) {
		
		UserSpringSecurity userSpringSecurity = new UserSpringSecurity();
		userSpringSecurity.setName(request.getName());
		userSpringSecurity.setEmail(request.getEmail());
		userSpringSecurity.setPassword(passwordEncoder.encode(request.getPassword()));
		
		userSpringSecurity = userSpringSecurityService.create(userSpringSecurity);
		
		return new AuthResponse(jwtService.generateToken(userSpringSecurity.getEmail()));
	}
	
	public AuthResponse authenticate(AuthRequest request) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()));
		
		final UserSpringSecurity userSpringSecurity = userSpringSecurityService.findByEmail(request.getEmail());
		return new AuthResponse(jwtService.generateToken(userSpringSecurity.getEmail()));
	}
}
