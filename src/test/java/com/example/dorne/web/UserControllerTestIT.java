package com.example.dorne.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.web.servlet.function.RequestPredicates.GET;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistration () throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .param("username", "andrey")
                        .param("email", "andrey@gmail.com")
                        .param("password", "secret")
                        .param("confirmPassword", "secret")
        ).andExpect(status().is3xxRedirection());
    }

    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testLoginPageShown() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-login"));
    }

    @Test
    void changeRoles() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/change-roles")
                        .param("email", "andrey@gmail.com")
                        .param("newRole", "Role_ADMIN")
        ).andExpect(status().is3xxRedirection());
    }



}