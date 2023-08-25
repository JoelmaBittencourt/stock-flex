package com.stock.flex.security.user;

import com.stock.flex.security.resource.request.response.PersonResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "persons")
public class UserSpringSecurity implements UserDetails {//Serializable,
    //private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_person_id")
    @SequenceGenerator(name = "gen_person_id", sequenceName = "seq_person_id", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "person_role")
    private Set<Integer> roles = new HashSet<>(Arrays.asList(RoleEnum.USER.getId()));

    public UserSpringSecurity() {
        super();
    }

    public UserSpringSecurity(Long id, String name, String email, String password, Set<RoleEnum> roles) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.setRoles(roles);
    }

    public UserSpringSecurity(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserSpringSecurity(PersonResponse dto) {
        this(dto.getName(), dto.getEmail(), dto.getPassword());
        this.setId(dto.getId());
        this.setStringRoles(dto.getRoles());
    }
//
//	public String getName() {
//		return name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public String getPassword() {
//		return password;
//	}

    public Set<RoleEnum> getRoles() {
        return roles.stream().map(r -> RoleEnum.fromId(r)).collect(Collectors.toSet());
    }

    public void setRoles(Set<RoleEnum> roles) {
        if (roles == null || roles.isEmpty())
            this.roles.clear();
        else
            this.roles = roles.stream().map(RoleEnum::getId).collect(Collectors.toSet());
    }

    public void setId(Long id) {
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

    public void setStringRoles(Set<String> roles) {
        if (roles == null || roles.isEmpty())
            this.roles.clear();
        else
            this.roles = roles.stream().map(s -> RoleEnum.fromDescription(s).getId()).collect(Collectors.toSet());
    }

    public void addRole(RoleEnum role) {
        this.roles.add(role.getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(RoleEnum.fromId(r).name()))
                .collect(Collectors.toSet());
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
