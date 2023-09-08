package com.stock.flex.security.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.stock.flex.resource.request.PersonResponse;
import com.stock.flex.entity.PersonEntity;
import com.stock.flex.entity.enums.Role;
import com.stock.flex.resource.handler.DuplicationException;
import com.stock.flex.resource.handler.NotFoundException;
import com.stock.flex.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	public PersonEntity findById(UUID id) {
		return repository.findById(id).orElseThrow(
				() -> new NotFoundException("Person not found: " + id));
	}
	
	public PersonEntity findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(
				() -> new NotFoundException("Person not found: " + email));
	}
	
	public List<PersonEntity> findAll() {
		return repository.findAll();
	}
	
	public PersonEntity create(PersonEntity personEntity) {
		personEntity.addRole(Role.USER);
		checkEmailDuplication(personEntity);
		return repository.save(personEntity);
	}
	
	public PersonResponse create(PersonResponse dto) {
		return new PersonResponse(create(new PersonEntity(dto)));
	}
	
	public PersonEntity update(PersonEntity personEntity) {
		checkEmailDuplication(personEntity);
		PersonEntity p = findById(personEntity.getId());
		p.setName(personEntity.getName());
		p.setEmail(personEntity.getEmail());
		p.setRoles(personEntity.getRoles());
		return repository.save(p);
	}
	
	public void delete(UUID id) {
		final PersonEntity p = findById(id);
		repository.delete(p);
	}
	
	private void checkEmailDuplication(PersonEntity personEntity) {
		final String email = personEntity.getEmail();
		if (email != null && email.length() > 0) {
			final UUID id = personEntity.getId();
			final PersonEntity p = repository.findByEmail(email).orElse(null);
			if (p != null && Objects.equals(p.getEmail(), email) && !Objects.equals(p.getId(), id)) {
				throw new DuplicationException("Email duplication: " + email);
			}
		}
	}

}
