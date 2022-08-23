package com.example.backenddemo.controllers;

import com.example.backenddemo.models.User;
import com.example.backenddemo.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.validateMockitoUsage;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("GET VERB return single user")
    void getSingleUser(){
        User mockUser= new User(1L,"Sam","johnson","samjo@gmail.com","1990-02-12");
        doReturn(Optional.of(mockUser)).when(userRepository).findById(1L);

//        mockMvc.perform(get(("/api/users/{id}",1L));

    }

}