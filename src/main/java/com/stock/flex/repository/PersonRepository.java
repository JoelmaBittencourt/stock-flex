package com.stock.flex.repository;


import com.stock.flex.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

	Optional<Person> findByEmail(String email);
}
