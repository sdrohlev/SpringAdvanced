package com.example.dorne.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRoleBindingModel {

    private String email;
    private String newRole;

    public UserRoleBindingModel() {
    }

    @NotBlank(message = "Please choose user")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "Please choose new user role")
    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }
}
