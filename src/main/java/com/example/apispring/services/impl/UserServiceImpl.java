package com.example.apispring.services.impl;

import com.example.apispring.domain.User;
import com.example.apispring.repositories.UserRepository;
import com.example.apispring.services.UserService;
import com.example.apispring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
    }
}
