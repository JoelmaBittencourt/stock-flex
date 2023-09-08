package com.stock.flex.config;

import com.stock.flex.entity.PersonEntity;
import com.stock.flex.entity.enums.Role;
import com.stock.flex.security.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseInitializationConfig {

    @Autowired
    private PersonService personService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            System.out.println("Initializing database...");

            final PersonEntity user = new PersonEntity("Joelma2", "joelmaty65a@mail.com", passwordEncoder.encode("123"));
            final PersonEntity admin = new PersonEntity("stockflex2", "stockflextryh@mail.com", passwordEncoder.encode("123"));

            admin.addRole(Role.ADMIN);

            personService.create(user);
            personService.create(admin);
        };
    }
}
