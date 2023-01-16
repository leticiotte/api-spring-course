package com.example.apispring.services.impl;

import com.example.apispring.domain.User;
import com.example.apispring.domain.dto.UserDTO;
import com.example.apispring.repositories.UserRepository;
import com.example.apispring.services.exceptions.DataIntegratyViolationException;
import com.example.apispring.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Letícia";
    public static final String EMAIL = "leticia@gmail.com";
    public static final String PASSWORD = "senha123";
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        Mockito.when(this.repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        User response = this.service.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        Mockito.when(this.repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException("Usuário não encontrado!"));

        try{
            this.service.findById(ID);
        }catch (Exception exception){
            Assertions.assertEquals(ObjectNotFoundException.class, exception.getClass());
            Assertions.assertEquals("Usuário não encontrado!", exception.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnsAListOfUsers() {
        int index = 0;
        Mockito.when(this.repository.findAll()).thenReturn(List.of(user));

        List<User> response = this.service.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(User.class, response.get(index).getClass());
        Assertions.assertEquals(ID, response.get(index).getId());
        Assertions.assertEquals(NAME, response.get(index).getName());
        Assertions.assertEquals(EMAIL, response.get(index).getEmail());
        Assertions.assertEquals(PASSWORD, response.get(index).getPassword());
    }

    @Test
    void whenCreateThenReturnsSuccess() {
        Mockito.when(this.repository.save(Mockito.any())).thenReturn(user);

        User response = this.service.create(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnsADataIntegrityViolationException() {
        Mockito.when(this.repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            this.service.create(userDTO);
        }catch (Exception exception){
            Assertions.assertEquals(DataIntegratyViolationException.class, exception.getClass());
            Assertions.assertEquals("E-mail já cadastrado no sistema!", exception.getMessage());
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}