package com.stock.flex.resource;

import java.util.List;
import java.util.UUID;

import com.stock.flex.resource.request.PersonResponse;
import com.stock.flex.entity.Person;
import com.stock.flex.security.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public ResponseEntity<List<PersonResponse>> findAll() {
		final List<Person> persons = service.findAll();
		final List<PersonResponse> dtos = persons.stream().map(p -> new PersonResponse(p)).toList();
		return ResponseEntity.ok(dtos);
	}

	@PostMapping
	public ResponseEntity<PersonResponse> create(@RequestBody PersonResponse dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return ResponseEntity.ok(service.create(dto));
	}

	@PutMapping
	public ResponseEntity<PersonResponse> update(@RequestBody PersonResponse dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return ResponseEntity.ok(service.create(dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
