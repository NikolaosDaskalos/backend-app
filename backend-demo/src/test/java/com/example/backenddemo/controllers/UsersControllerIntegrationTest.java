package com.example.backenddemo.controllers;

import com.example.backenddemo.models.User;
import com.example.backenddemo.servicesImpl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Users Controller Integration Tests")
public class UsersControllerIntegrationTest {
    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    private User user = new User(1L,"John","Smith","johnsmith@gmail.com","1993-02-22");


    @Test
    @DisplayName("Get all users successfully")
    void getAllUsersTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(new ArrayList<User>());
        mockMvc.perform(get("/api/users/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get a user by id successfully")
    void getUserByIdTest() throws Exception {
        when(userService.getUserById(1l)).thenReturn(user);
        mockMvc.perform(get("/api/users/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.userId").value(1L))
                        .andExpect(jsonPath("$.firstName").value("John"))
                        .andExpect(jsonPath("$.lastName").isNotEmpty())
                        .andExpect(jsonPath("$.email").value("johnsmith@gmail.com"))
                         .andExpect(jsonPath("$.dateOfBirth").value("1993-02-22"));
    }

    @Test
    @DisplayName("Post new user successfully")
    void postNewUserTest() throws Exception {
        when((userService.saveUser(user))).thenReturn(user);

        mockMvc.perform(post("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"post@revbnation.com\"," +
                                 "\"firstName\": \"post\"," +
                                 "\"lastName\": \"postAgain \"," +
                                 "\"dateOfBirth\": \"1977-12-26\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Delete user successfully")
    void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/api/users/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Put request update user successfully")
    void putRequestUpdateUserTest() throws Exception {
        User update = new User(3L,"George","Brown","gbrowm@hotmail.com","1984-09-12");
        when(userService.updateUser(1L,update)).thenReturn(update);

        mockMvc.perform(put("/api/users/{id}",user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"gbrowm@hotmail.com\"," +
                                "\"firstName\": \"George\"," +
                                "\"lastName\": \"Brown \"," +
                                "\"dateOfBirth\": \"1984-09-12\"}"))
                        .andExpect(status().isOk());
    }

}
