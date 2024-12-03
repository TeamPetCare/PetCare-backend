package com.application.petcare;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "PsicoCare API",
				version = "0.2",
				description = "Routes documentation",
				contact = @Contact(
						name = "PetCare Support",
						email = "support@petcare.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "http://springdoc.org"
				)
		),
		security = @SecurityRequirement(name = "bearer")
)
@SecurityScheme(
		name = "bearer",
		scheme = "bearer",
		bearerFormat = "JWT",
		type = SecuritySchemeType.HTTP,
		in = SecuritySchemeIn.HEADER
)
@EnableFeignClients
@SpringBootApplication
public class PetCareRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetCareRestApplication.class, args);

	}
}