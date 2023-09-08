package com.stock.flex.repository;


import com.stock.flex.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {

	Optional<PersonEntity> findByEmail(String email);
}
