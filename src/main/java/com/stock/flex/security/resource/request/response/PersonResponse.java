package com.stock.flex.security.resource.request.response;

import com.stock.flex.security.user.RoleEnum;
import com.stock.flex.security.user.UserSpringSecurity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class PersonResponse {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private Set<String> roles = new HashSet<>();
	
	public PersonResponse() {
		super();
	}

	public PersonResponse(Long id, String name, String email, Set<String> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}
	
	public PersonResponse(UserSpringSecurity userSpringSecurity) {
		super();
		this.id = userSpringSecurity.getId();
		this.name = userSpringSecurity.getName();
		this.email = userSpringSecurity.getEmail();
		this.setRoles(userSpringSecurity.getRoles());
	}

	public void setRoles(Set<RoleEnum> roles) {
		this.roles = roles.stream().map(r -> r.getDescription()).collect(Collectors.toSet());
	}
	
}
