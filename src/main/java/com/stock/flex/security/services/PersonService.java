package com.stock.flex.security.services;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.stock.flex.resource.request.PersonResponse;
import com.stock.flex.entity.Person;
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
	
	public Person findById(UUID id) {
		return repository.findById(id).orElseThrow(
				() -> new NotFoundException("Person not found: " + id));
	}
	
	public Person findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(
				() -> new NotFoundException("Person not found: " + email));
	}
	
	public List<Person> findAll() {
		return repository.findAll();
	}
	
	public Person create(Person person) {
		person.addRole(Role.USER);
		checkEmailDuplication(person);
		return repository.save(person);
	}
	
	public PersonResponse create(PersonResponse dto) {
		return new PersonResponse(create(new Person(dto)));
	}
	
	public Person update(Person person) {
		checkEmailDuplication(person);
		Person p = findById(person.getId());
		p.setName(person.getName());
		p.setEmail(person.getEmail());
		p.setRoles(person.getRoles());
		return repository.save(p);
	}
	
	public void delete(UUID id) {
		final Person p = findById(id);
		repository.delete(p);
	}
	
	private void checkEmailDuplication(Person person) {
		final String email = person.getEmail();
		if (email != null && email.length() > 0) {
			final UUID id = person.getId();
			final Person p = repository.findByEmail(email).orElse(null);
			if (p != null && Objects.equals(p.getEmail(), email) && !Objects.equals(p.getId(), id)) {
				throw new DuplicationException("Email duplication: " + email);
			}
		}
	}

}
