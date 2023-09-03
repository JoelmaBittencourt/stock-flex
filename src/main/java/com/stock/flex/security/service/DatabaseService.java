package com.stock.flex.security.service;

import com.stock.flex.entity.Person;
import com.stock.flex.entity.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class DatabaseService {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void initializeDatabase() {

		System.out.println("Initializing database...");
		
		final Person user = new Person("Emma", "emma@mail.com", passwordEncoder.encode("111"));
		final Person admin = new Person("Anna", "anna@mail.com", passwordEncoder.encode("222"));
		
		admin.addRole(Role.ADMIN);
	
		System.out.println(personService.create(user));
		System.out.println(personService.create(admin));
		
	}
}
