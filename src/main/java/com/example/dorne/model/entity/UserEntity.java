package com.example.dorne.model.entity;

import com.example.dorne.model.entity.enums.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    private String username;
    private String password;
    private String email;
    private UserRoleEnum role;

    //private List<Event>;
    //private List<Place>;
    //TODO:

    public UserEntity() {
    }

    public UserEntity(String username, String password, String email, UserRoleEnum userRoleEnum) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = userRoleEnum;
    }

    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Email
    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Enumerated(value = EnumType.STRING)
    public UserRoleEnum getRole() {
        return role;
    }

    public UserEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
