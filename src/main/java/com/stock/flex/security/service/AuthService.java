package com.stock.flex.security.service;

import com.stock.flex.resource.request.AuthRequest;
import com.stock.flex.resource.response.AuthResponse;
import com.stock.flex.resource.request.RegisterRequest;
import com.stock.flex.entity.PersonEntity;
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
		
		PersonEntity personEntity = new PersonEntity();
		personEntity.setName(dto.name());
		personEntity.setEmail(dto.email());
		personEntity.setPassword(passwordEncoder.encode(dto.password()));
		
		personEntity = personService.create(personEntity);
		
		return new AuthResponse(jwtService.generateToken(personEntity.getEmail()));
	}
	
	public AuthResponse authenticate(AuthRequest dto) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.email(),
						dto.password()));
		
		final PersonEntity personEntity = personService.findByEmail(dto.email());
		return new AuthResponse(jwtService.generateToken(personEntity.getEmail()));
	}
	
	
	
}
