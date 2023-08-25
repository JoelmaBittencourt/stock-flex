package com.stock.flex.security.controllers;

import java.util.List;

import com.stock.flex.security.dtos.PersonDTO;
import com.stock.flex.security.entities.Person;
import com.stock.flex.security.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public ResponseEntity<List<PersonDTO>> findAll() {
		final List<Person> persons = service.findAll();
		final List<PersonDTO> dtos = persons.stream().map(p -> new PersonDTO(p)).toList();
		return ResponseEntity.ok(dtos);
	}
	
	public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return ResponseEntity.ok(service.create(dto));
	}
	
	public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return ResponseEntity.ok(service.create(dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
