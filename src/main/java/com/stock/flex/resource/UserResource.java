package com.stock.flex.resource;

import java.util.List;
import java.util.UUID;

import com.stock.flex.resource.response.UserResponse;
import com.stock.flex.entity.UserEntity;
import com.stock.flex.security.service.UserService;
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
	private UserService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> findAll() {
		final List<UserEntity> personEntities = service.findAll();
		final List<UserResponse> dtos = personEntities.stream().map(p -> new UserResponse(p)).toList();
		return ResponseEntity.ok(dtos);
	}

	@PostMapping
	public ResponseEntity<UserResponse> create(@RequestBody UserResponse dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return ResponseEntity.ok(service.create(dto));
	}

	@PutMapping
	public ResponseEntity<UserResponse> update(@RequestBody UserResponse dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return ResponseEntity.ok(service.create(dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
