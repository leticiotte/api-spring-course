package com.example.apispring.services.impl;

import com.example.apispring.domain.User;
import com.example.apispring.domain.dto.UserDTO;
import com.example.apispring.repositories.UserRepository;
import com.example.apispring.services.UserService;
import com.example.apispring.services.exceptions.DataIntegratyViolationException;
import com.example.apispring.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> user = this.repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
    }

    public List<User> findAll(){
        return this.repository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return this.repository.save(mapper.map(userDTO, User.class));
    }

    private void findByEmail(UserDTO userDTO){
        Optional<User> user = this.repository.findByEmail(userDTO.getEmail());
        if(user.isPresent()){
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
