package com.stock.flex.useCase;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.stock.flex.resource.response.UserResponse;
import com.stock.flex.entity.UserEntity;
import com.stock.flex.entity.enums.Role;
import com.stock.flex.exception.DuplicationEmailException;
import com.stock.flex.exception.NotFoundException;
import com.stock.flex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserUseCase {
	
	@Autowired
	private UserRepository repository;
	
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
	
	public UserResponse create(UserResponse response) {
		return new UserResponse(create(new UserEntity(response)));
	}
	
	public UserEntity update(UserEntity userEntity) {
		checkEmailDuplication(userEntity);
		UserEntity entity = findById(userEntity.getId());
		entity.setName(userEntity.getName());
		entity.setEmail(userEntity.getEmail());
		entity.setRoles(userEntity.getRoles());
		return repository.save(entity);
	}
	
	public void delete(UUID id) {
		final UserEntity entity = findById(id);
		repository.delete(entity);
	}
	
	private void checkEmailDuplication(UserEntity userEntity) {
		final String email = userEntity.getEmail();
		if (email != null && !email.isEmpty()) {
			final UUID id = userEntity.getId();
			final UserEntity entity = repository.findByEmail(email).orElse(null);
			if (entity != null && Objects.equals(entity.getEmail(), email) && !Objects.equals(entity.getId(), id)) {
				throw new DuplicationEmailException("Email duplication: " + email);
			}
		}
	}

}
