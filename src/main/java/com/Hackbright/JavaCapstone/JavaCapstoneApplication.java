package com.Hackbright.JavaCapstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.Hackbright.JavaCapstone.configuration")
@EnableJpaRepositories("com.Hackbright.JavaCapstone.repositories")
public class
JavaCapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaCapstoneApplication.class, args);
	}

}
