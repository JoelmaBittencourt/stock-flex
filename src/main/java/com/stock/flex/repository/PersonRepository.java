package com.stock.flex.repository;


import com.stock.flex.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<UserEntity, UUID> {

	Optional<UserEntity> findByEmail(String email);
}
