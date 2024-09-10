package com.application.petcare.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customOpenApi() {
        return GroupedOpenApi.builder()
                .group("petcare")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info()
                        .title("PetCare API - Documentation")
                        .description("API de agendamentos para PetShop")
                        .version("1.0")
                        .contact(new Contact().name("PetCare Support").email("support@petcare.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))))
                .build();
    }
}
