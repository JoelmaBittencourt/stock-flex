package com.stock.flex.security.services;

import com.stock.flex.resource.request.AuthRequestDTO;
import com.stock.flex.resource.request.AuthResponseDTO;
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
	
	public AuthResponseDTO register(RegisterRequestDTO dto) {
		
		Person person = new Person();
		person.setName(dto.getName());
		person.setEmail(dto.getEmail());
		person.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		person = personService.create(person);
		
		return new AuthResponseDTO(jwtService.generateToken(person.getEmail()));
	}
	
	public AuthResponseDTO authenticate(AuthRequestDTO dto) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.getEmail(), 
						dto.getPassword()));
		
		final Person person = personService.findByEmail(dto.getEmail());
		return new AuthResponseDTO(jwtService.generateToken(person.getEmail()));
	}
	
	
	
}
