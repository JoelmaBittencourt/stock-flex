package com.stock.flex.security.resource.request;

import com.stock.flex.security.resource.request.response.PersonResponse;
import com.stock.flex.security.user.UserSpringSecurityService;
import com.stock.flex.security.user.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class UserSpringSecurityResource {
	
	@Autowired
	private UserSpringSecurityService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public ResponseEntity<List<PersonResponse>> findAll() {
		final List<UserSpringSecurity> userSpringSecurities = service.findAll();
		final List<PersonResponse> dtos = userSpringSecurities.stream().map(p -> new PersonResponse(p)).toList();
		return ResponseEntity.ok(dtos);
	}
	
	public ResponseEntity<PersonResponse> create(@RequestBody PersonResponse dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return ResponseEntity.ok(service.create(dto));
	}
	
	public ResponseEntity<PersonResponse> update(@RequestBody PersonResponse dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return ResponseEntity.ok(service.create(dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
