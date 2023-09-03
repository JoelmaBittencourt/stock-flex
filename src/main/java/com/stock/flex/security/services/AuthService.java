package com.stock.flex.security.services;

import com.stock.flex.resource.request.AuthRequest;
import com.stock.flex.resource.request.AuthResponse;
import com.stock.flex.resource.request.RegisterRequestDTO;
import com.stock.flex.entity.Person;
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
	
	public AuthResponse register(RegisterRequestDTO dto) {
		
		Person person = new Person();
		person.setName(dto.getName());
		person.setEmail(dto.getEmail());
		person.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		person = personService.create(person);
		
		return new AuthResponse(jwtService.generateToken(person.getEmail()));
	}
	
	public AuthResponse authenticate(AuthRequest dto) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.email(),
						dto.password()));
		
		final Person person = personService.findByEmail(dto.email());
		return new AuthResponse(jwtService.generateToken(person.getEmail()));
	}
	
	
	
}
