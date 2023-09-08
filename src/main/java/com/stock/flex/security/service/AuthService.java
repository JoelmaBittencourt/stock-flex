package com.stock.flex.security.service;

import com.stock.flex.resource.request.AuthRequest;
import com.stock.flex.resource.response.AuthResponse;
import com.stock.flex.resource.request.RegisterRequest;
import com.stock.flex.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthResponse register(RegisterRequest dto) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setName(dto.name());
		userEntity.setEmail(dto.email());
		userEntity.setPassword(passwordEncoder.encode(dto.password()));
		
		userEntity = personService.create(userEntity);
		
		return new AuthResponse(jwtService.generateToken(userEntity.getEmail()));
	}
	
	public AuthResponse authenticate(AuthRequest dto) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.email(),
						dto.password()));
		
		final UserEntity userEntity = personService.findByEmail(dto.email());
		return new AuthResponse(jwtService.generateToken(userEntity.getEmail()));
	}
	
	
	
}
