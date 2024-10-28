package com.application.petcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PetCareRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetCareRestApplication.class, args);

	}
}