package com.example.apispring.config;

import com.example.apispring.domain.User;
import com.example.apispring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {
    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){
        User u1 = new User(null, "Letícia Gonçalves", "leticia@gmail.com", "senha123");
        User u2 = new User(null, "Danúbia Gonçalves", "danubia@gmail.com", "senha1234");

        repository.saveAll(List.of(u1, u2));
    }
}
