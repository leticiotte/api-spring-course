package com.example.apispring.services;

import com.example.apispring.domain.User;
import com.example.apispring.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO userDTO);
    User update(UserDTO userDTO);
    void delete(Integer id);
}
