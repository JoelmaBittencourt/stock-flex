package com.stock.flex.security.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.stock.flex.resource.request.UserResponse;
import com.stock.flex.entity.UserEntity;
import com.stock.flex.entity.enums.Role;
import com.stock.flex.exception.DuplicationEmailException;
import com.stock.flex.exception.NotFoundException;
import com.stock.flex.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	
	@Autowired
	private PersonRepository repository;
	
	public UserEntity findById(UUID id) {
		return repository.findById(id).orElseThrow(
				() -> new NotFoundException("Person not found: " + id));
	}
	
	public UserEntity findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(
				() -> new NotFoundException("Person not found: " + email));
	}
	
	public List<UserEntity> findAll() {
		return repository.findAll();
	}
	
	public UserEntity create(UserEntity userEntity) {
		userEntity.addRole(Role.USER);
		checkEmailDuplication(userEntity);
		return repository.save(userEntity);
	}
	
	public UserResponse create(UserResponse dto) {
		return new UserResponse(create(new UserEntity(dto)));
	}
	
	public UserEntity update(UserEntity userEntity) {
		checkEmailDuplication(userEntity);
		UserEntity p = findById(userEntity.getId());
		p.setName(userEntity.getName());
		p.setEmail(userEntity.getEmail());
		p.setRoles(userEntity.getRoles());
		return repository.save(p);
	}
	
	public void delete(UUID id) {
		final UserEntity p = findById(id);
		repository.delete(p);
	}
	
	private void checkEmailDuplication(UserEntity userEntity) {
		final String email = userEntity.getEmail();
		if (email != null && email.length() > 0) {
			final UUID id = userEntity.getId();
			final UserEntity p = repository.findByEmail(email).orElse(null);
			if (p != null && Objects.equals(p.getEmail(), email) && !Objects.equals(p.getId(), id)) {
				throw new DuplicationEmailException("Email duplication: " + email);
			}
		}
	}

}
