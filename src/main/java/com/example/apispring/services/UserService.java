package com.example.apispring.services;

import com.example.apispring.domain.User;

public interface UserService {
    User findById(Integer id);
}
