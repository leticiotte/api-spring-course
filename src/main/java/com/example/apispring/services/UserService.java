package com.example.apispring.services;

import com.example.apispring.domain.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
}
