package com.stock.flex.config;

import com.stock.flex.entity.UserEntity;
import com.stock.flex.entity.enums.Role;
import com.stock.flex.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseInitializationConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            System.out.println("Initializing database...");

            final UserEntity joelma = new UserEntity("Joelma", "joelma@mail.com", passwordEncoder.encode("123"));
            final UserEntity stockflex = new UserEntity("stockflex", "stockflex@gmail.com", passwordEncoder.encode("123"));

            stockflex.addRole(Role.ADMIN);
            joelma.addRole(Role.ADMIN);

            userService.create(stockflex);
            userService.create(joelma);
        };
    }
}
