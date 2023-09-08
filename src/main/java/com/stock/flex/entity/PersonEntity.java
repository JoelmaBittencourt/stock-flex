package com.stock.flex.entity;


import com.stock.flex.resource.request.PersonResponse;
import com.stock.flex.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "person")
public class PersonEntity implements  UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@Column(nullable = false, length = 60)
	private String password;

	@Column(name = "role")
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "person_role")
	private Set<Integer> roles = new HashSet<>(Arrays.asList(Role.USER.getId()));



	@ManyToOne
	@JoinColumn(name = "user_id")
	private PersonEntity user;

	@ManyToOne
	@JoinColumn(name = "stock_id")
	private StockEntity stock;

	public PersonEntity() {
		super();
	}

	public PersonEntity(UUID id, String name, String email, String password, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.setRoles(roles);
	}

	public PersonEntity(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public PersonEntity(PersonResponse dto) {
		this(dto.getName(), dto.getEmail(), dto.getPassword());
		this.setId(dto.getId());
		this.setStringRoles(dto.getRoles());
	}


	public Set<Role> getRoles() {
		return roles.stream().map(r -> Role.fromId(r)).collect(Collectors.toSet());
	}


	public void setRoles(Set<Role> roles) {
		if (roles == null || roles.isEmpty())
			this.roles.clear();
		else
			this.roles = roles.stream().map(r -> r.getId()).collect(Collectors.toSet());
	}

	public void setStringRoles(Set<String> roles) {
		if (roles == null || roles.isEmpty())
			this.roles.clear();
		else
			this.roles = roles.stream().map(s -> Role.fromDescription(s).getId()).collect(Collectors.toSet());
	}

	public void addRole(Role role) {
		this.roles.add(role.getId());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(r -> new SimpleGrantedAuthority(Role.fromId(r).name()))
				.collect(Collectors.toSet());
	}

	@Override
	public String getPassword() {
		return  password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", email=" + email + ", roles=" + getRoles() + "]";
	}

}