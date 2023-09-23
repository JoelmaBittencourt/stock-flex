package com.stock.flex.resource;

import java.util.List;
import java.util.UUID;

import com.stock.flex.resource.response.UserResponse;
import com.stock.flex.entity.UserEntity;
import com.stock.flex.usecase.UserUseCase;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Hidden
@RestController
@RequestMapping("/person")
public class UserResource {
	
	@Autowired
	private UserUseCase service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> findAll() {
		final List<UserEntity> personEntities = service.findAll();
		final List<UserResponse> responses = personEntities.stream().map(UserResponse::new).toList();
		return ResponseEntity.ok(responses);
	}

	@PostMapping
	public ResponseEntity<UserResponse> create(@RequestBody UserResponse response) {
		response.setPassword(passwordEncoder.encode(response.getPassword()));
		return ResponseEntity.ok(service.create(response));
	}

	@PutMapping
	public ResponseEntity<UserResponse> update(@RequestBody UserResponse response) {
		response.setPassword(passwordEncoder.encode(response.getPassword()));
		return ResponseEntity.ok(service.create(response));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
