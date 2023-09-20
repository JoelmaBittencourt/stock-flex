package com.stock.flex.resource.response;



import com.stock.flex.entity.UserEntity;
import com.stock.flex.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
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


	public void setRoles(Set<Role> roles) {
		this.roles = roles.stream().map(Role::getDescription).collect(Collectors.toSet());
	}
	
}
