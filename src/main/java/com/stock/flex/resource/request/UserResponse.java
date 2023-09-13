package com.stock.flex.resource.request;



import com.stock.flex.entity.UserEntity;
import com.stock.flex.entity.enums.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserResponse {
	
	private UUID id;
	private String name;
	private String email;
	private String password;
	private Set<String> roles = new HashSet<>();
	
	public UserResponse() {
		super();
	}

	public UserResponse(UUID id, String name, String email, Set<String> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}
	
	public UserResponse(UserEntity userEntity) {
		super();
		this.id = userEntity.getId();
		this.name = userEntity.getName();
		this.email = userEntity.getEmail();
		this.setRoles(userEntity.getRoles());
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public Set<String> getRoles() {
		return roles;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles.stream().map(r -> r.getDescription()).collect(Collectors.toSet());
	}
	
}
