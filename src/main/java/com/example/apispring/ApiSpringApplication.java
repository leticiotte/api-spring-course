package com.example.apispring;

import com.example.apispring.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSpringApplication.class, args);
		User user = new User(1, "Letícia", "leticia@gmail.com", "senha123");
	}

}
