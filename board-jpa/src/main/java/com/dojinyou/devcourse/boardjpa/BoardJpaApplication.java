package com.dojinyou.devcourse.boardjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BoardJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardJpaApplication.class, args);
	}
}
