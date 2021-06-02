package com.etherapy.etherapyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EtherapyprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtherapyprojectApplication.class, args);
	}

}
