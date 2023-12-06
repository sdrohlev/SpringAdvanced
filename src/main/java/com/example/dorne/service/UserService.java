package com.example.dorne.service;

import com.example.dorne.model.entity.UserEntity;
import com.example.dorne.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    UserServiceModel findByEmail(String email);

    UserEntity findByEmailAuth(String email);

    void register(UserServiceModel map);

    List<UserEntity> findAllUsers();

    boolean isUserRoleEqual(String email, String newRole);

    void changeUserRole(String email, String newRole);

    boolean usernameExists(String username);

    void changeUsername(String email, String username);
}

