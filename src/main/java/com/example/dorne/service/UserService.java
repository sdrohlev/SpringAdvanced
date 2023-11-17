package com.example.dorne.service;

import com.example.dorne.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel findByEmail(String email);

    void register(UserServiceModel map);
}

