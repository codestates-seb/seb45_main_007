package com.codestates.main07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Main07Application {

	public static void main(String[] args) {
		SpringApplication.run(Main07Application.class, args);
	}

}
