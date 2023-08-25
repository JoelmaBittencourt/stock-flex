package com.stock.flex.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSpringSecurityRepository extends JpaRepository<UserSpringSecurity, Long> {

	Optional<UserSpringSecurity> findByEmail(String email);
}
