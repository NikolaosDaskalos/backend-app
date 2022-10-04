package com.example.backenddemo.services;

import com.example.backenddemo.models.User;
import com.example.backenddemo.repositories.UserRepository;
import com.example.backenddemo.servicesImpl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("User Service implementation class test")
class UserServiceTest {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepository userRepository;

    User mockUser = new User(1L,"John","Smith","johnsmith@gmail.com","1993-02-22");

    @Test
    @DisplayName("getAllUsers Successfully")
    void getAllUsers() {
        User mockUser2 = new User(2L,"Jim","Brown","jbrownth@hotmail.com","1981-05-10");
        when(userRepository.findAll()).thenReturn(Arrays.asList(mockUser,mockUser2));
        assertAll(
                ()-> assertNotNull(userServiceImpl.getAllUsers()),
                ()-> assertSame(userRepository.findAll(), userServiceImpl.getAllUsers())
        );
    }

    @Test
    @DisplayName("getUserById Successfully")
    void getUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userRepository.getReferenceById(1L)).thenReturn(mockUser);
        assertAll(
            ()->assertNotNull(userServiceImpl.getUserById(1L)),
                        ()->assertSame(mockUser, userServiceImpl.getUserById(1L))
        );
    }

    @Test
    @DisplayName("getUserById throws Exception Successfully")
    void getUserByIdThrowsException() {
        when(userRepository.findById(mockUser.getUserId())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class ,()-> userServiceImpl.getUserById(mockUser.getUserId()));
    }

    @Test
    @DisplayName("saveUser Successfully")
    void saveUser() {
        when(userRepository.findByEmail("johnsmith@gmail.com")).thenReturn(null);
        when(userRepository.saveAndFlush(mockUser)).thenReturn(mockUser);
        assertSame(mockUser, userServiceImpl.saveUser(mockUser));
    }

    @Test
    @DisplayName("saveUser throws exception Successfully")
    void saveUserThrowsException() {
        when(userRepository.findByEmail("johnsmith@gmail.com")).thenReturn(mockUser);
        assertThrows(ResponseStatusException.class,()-> userServiceImpl.saveUser(mockUser));
    }

    @Test
    @DisplayName("deleteUser Successfully")
    void deleteUserById() {
        userServiceImpl.deleteUserById(1L);
        verify(userRepository,times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("User Update Successfully")
    void updateUser() {
        User userUpdate = new User(5l,"Jim","Brown","jbrownth@hotmail.com","1981-05-10");
        when(userRepository.getReferenceById(1L)).thenReturn(mockUser);
        userServiceImpl.updateUser(1L,userUpdate);
        verify(userRepository).saveAndFlush(mockUser);
    }
}