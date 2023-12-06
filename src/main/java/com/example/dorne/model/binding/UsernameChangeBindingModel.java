package com.example.dorne.model.binding;

import jakarta.validation.constraints.Size;

public class UsernameChangeBindingModel {

    private String username;

    public UsernameChangeBindingModel() {
    }

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
