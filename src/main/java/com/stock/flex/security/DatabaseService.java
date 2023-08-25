package com.stock.flex.security;

import com.stock.flex.security.user.PersonService;
import com.stock.flex.security.user.RoleEnum;
import com.stock.flex.security.user.UserSpringSecurity;
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

		final UserSpringSecurity user1 = new UserSpringSecurity("Emma", "emma@mail.com", passwordEncoder.encode("111"));
		final UserSpringSecurity user2 = new UserSpringSecurity("Jhon", "jhon@mail.com", passwordEncoder.encode("222"));
		final UserSpringSecurity admin = new UserSpringSecurity("Anna", "anna@mail.com", passwordEncoder.encode("333"));

		admin.addRole(RoleEnum.ADMIN);

		System.out.println(personService.create(user1));
		System.out.println(personService.create(user2));
		System.out.println(personService.create(admin));

		System.out.println("Database initialized!");
	}
}
