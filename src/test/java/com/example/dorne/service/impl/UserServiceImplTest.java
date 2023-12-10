package com.example.dorne.service.impl;

import com.example.dorne.model.binding.UserRegisterBindingModel;
import com.example.dorne.model.entity.UserEntity;
import com.example.dorne.model.entity.enums.UserRoleEnum;
import com.example.dorne.model.service.UserServiceModel;
import com.example.dorne.repository.UserRepository;
import com.example.dorne.util.TestDataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private ModelMapper mockModelMapper;
    private UserServiceImpl userServiceImplToTest;



    @BeforeEach
    void setUp() {
        userServiceImplToTest =
                new UserServiceImpl(mockUserRepository, mockModelMapper, mockPasswordEncoder);
    }

    @Test
    void findByEmailTest() {

        //Arrange
        UserEntity testUser = new UserEntity("test", "1234", "test@gmail.com", UserRoleEnum.USER);
        mockUserRepository.save(testUser);
        when(mockUserRepository.findByEmail(testUser.getEmail()))
                .thenReturn(Optional.of(testUser));

        //Act
        userServiceImplToTest.findByEmail(testUser.getEmail());

        //Assert
        Assertions.assertTrue(mockUserRepository.findByEmail(testUser.getEmail()).isPresent());
    }

    @Test
    void findByEmailAuth() {

    }

    @Test
    void registerTest() {

        //Arrange
        UserServiceModel userServiceModel = new UserServiceModel()
                .setUsername("test")
                .setEmail("test@gmail.com")
                .setPassword("1234");

        //Act
        userServiceImplToTest.register(userServiceModel);

        //Assert
        Mockito.verify(mockUserRepository).save(any());

    }

    @Test
    void findAllUsersTest() {

        //Arrange
        UserEntity testUser1 = new UserEntity("test1", "1234", "test1@gmail.com", UserRoleEnum.USER);
        UserEntity testUser2 = new UserEntity("test2", "1234", "test2@gmail.com", UserRoleEnum.USER);
        mockUserRepository.save(testUser1);
        mockUserRepository.save(testUser2);

        when(mockUserRepository.findAll())
                .thenReturn(List.of(testUser1, testUser2));

        //Act
        List<UserEntity> allUsers = userServiceImplToTest.findAllUsers();

        //Assert
        Assertions.assertEquals(allUsers.size(), 2);
    }

    @Test
    void isUserRoleEqual() {
    }

    @Test
    void changeUserRoleTest() {
        //Arrange
        UserEntity testUser = new UserEntity("test", "1234", "test@gmail.com", UserRoleEnum.USER);
        when(mockUserRepository.findByEmail(testUser.getEmail()))
                .thenReturn(Optional.of(testUser));

        //Act
        userServiceImplToTest.changeUserRole(testUser.getEmail(), UserRoleEnum.ADMIN.name());

        //Assert
        Assertions.assertEquals(testUser.getRole(), UserRoleEnum.ADMIN);
    }

    @Test
    void usernameExists() {
        //Arrange
        //Act
        //Assert
    }

    @Test
    void changeUsernameTest() {
        //Arrange
        UserEntity testUser = new UserEntity("test", "1234", "test@gmail.com", UserRoleEnum.USER);
        when(mockUserRepository.findByEmail(testUser.getEmail()))
                .thenReturn(Optional.of(testUser));

        //Act
        userServiceImplToTest.changeUsername(testUser.getEmail(), "newUsername");

        //Assert
        Assertions.assertEquals(testUser.getUsername(), "newUsername");
    }
}