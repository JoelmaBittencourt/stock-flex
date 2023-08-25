package com.stock.flex.security.user;

import java.util.Arrays;
import java.util.Objects;

public enum RoleEnum {
	
	USER(1, "User"),
	ADMIN(2, "Administrator");
	
	private Integer id;
	private String description;

	RoleEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static RoleEnum fromId(Integer id) {
		return Arrays.stream(RoleEnum.values())
				.filter(role -> Objects.equals(role.getId(), id))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown role: " + id));
	}
	
	public static RoleEnum fromDescription(String description) {
		return Arrays.stream(RoleEnum.values())
				.filter(role -> Objects.equals(role.getDescription(), description))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown role: " + description));	
	}
	
}
