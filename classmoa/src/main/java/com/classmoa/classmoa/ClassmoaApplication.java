package com.classmoa.classmoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClassmoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassmoaApplication.class, args);
	}

}
