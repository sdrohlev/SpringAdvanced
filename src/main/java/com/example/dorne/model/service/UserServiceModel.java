package com.example.dorne.model.service;

import com.example.dorne.model.entity.enums.UserRoleEnum;

public class UserServiceModel {

    private String username;
    private String password;
    private String email;
    private UserRoleEnum role;


    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserServiceModel setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
