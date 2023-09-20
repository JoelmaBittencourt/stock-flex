package com.stock.flex.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .components(new Components()
                            .addSecuritySchemes("bearer-key",
                                    new SecurityScheme()
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")))
                    .info(new Info()
                            .title("Stock-flex")
                            .description("API Rest da aplicação Stock-flex, oferecendo recursos para registro e autenticação de usuários, permitindo que os usuários criem e gerenciem seus estoques personalizados")
                            .contact(new Contact()
                                    .name("Time Backend")
                                    .url("linkedin")
                                    .email("joelmadev@gmail.com")));
        }
    }
