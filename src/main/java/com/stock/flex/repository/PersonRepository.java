package com.stock.flex.repository;


import com.stock.flex.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByEmail(String email);
}