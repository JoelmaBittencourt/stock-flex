package com.stock.flex.security.user;

import com.stock.flex.security.handler.DuplicationException;
import com.stock.flex.security.handler.NotFoundException;
import com.stock.flex.security.resource.request.response.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonService {
	
	@Autowired
	private UserSpringSecurityRepository repository;
	
	public UserSpringSecurity findById(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new NotFoundException("Person not found: " + id));
	}
	
	public UserSpringSecurity findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(
				() -> new NotFoundException("Person not found: " + email));
	}
	
	public List<UserSpringSecurity> findAll() {
		return repository.findAll();
	}
	
	public UserSpringSecurity create(UserSpringSecurity userSpringSecurity) {
		userSpringSecurity.setId(null);
		userSpringSecurity.addRole(RoleEnum.USER);
		checkEmailDuplication(userSpringSecurity);
		return repository.save(userSpringSecurity);
	}
	
	public PersonResponse create(PersonResponse dto) {
		return new PersonResponse(create(new UserSpringSecurity(dto)));
	}
	
	public UserSpringSecurity update(UserSpringSecurity userSpringSecurity) {
		checkEmailDuplication(userSpringSecurity);
		UserSpringSecurity p = findById(userSpringSecurity.getId());
		p.setName(userSpringSecurity.getName());
		p.setEmail(userSpringSecurity.getEmail());
		p.setRoles(userSpringSecurity.getRoles());
		return repository.save(p);
	}
	
	public void delete(Long id) {
		final UserSpringSecurity p = findById(id);
		repository.delete(p);
	}
	
	private void checkEmailDuplication(UserSpringSecurity userSpringSecurity) {
		final String email = userSpringSecurity.getEmail();
		if (email != null && !email.isEmpty()) {
			final Long id = userSpringSecurity.getId();
			final UserSpringSecurity p = repository.findByEmail(email).orElse(null);
			if (p != null && Objects.equals(p.getEmail(), email) && !Objects.equals(p.getId(), id)) {
				throw new DuplicationException("Email duplication: " + email);
			}
		}
	}

}
