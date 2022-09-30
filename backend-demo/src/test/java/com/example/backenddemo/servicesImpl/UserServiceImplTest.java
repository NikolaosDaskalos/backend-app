package com.example.backenddemo.servicesImpl;

import com.example.backenddemo.models.User;
import com.example.backenddemo.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("User Service implementation class test")
class UserServiceImplTest {

    private UserRepository userRepository= mock(UserRepository.class);
    private UserServiceImpl userServiceImpl =new UserServiceImpl(userRepository);
    User user = new User(1l,"John","Smith","johnsmith@gmail.com","1993-02-22");

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("getAllUsers Successfully")
    void getAllUsers() {
        userServiceImpl.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("getUserById Successfully")
    void getUserById() {
        Long id=1l;
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(userServiceImpl.getUserById(user.getUserId())).thenReturn(user);
        userServiceImpl.getUserById(id);
        verify(userRepository).getReferenceById(id);
        assertEquals(user,userServiceImpl.getUserById(user.getUserId()));
    }

    @Test
    @DisplayName("getUserById throws Exception Successfully")
    void getUserByIdThrowsException() {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(null));
        assertThrows(ResponseStatusException.class ,()->userServiceImpl.getUserById(user.getUserId()));
    }

    @Test
    @DisplayName("saveUser Successfully")
    void saveUser() {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(userServiceImpl.saveUser(user)).thenReturn(user);
        assertEquals(user,userServiceImpl.saveUser(user));
    }

    @Test
    @DisplayName("saveUser throws exception Successfully")
    void saveUserThrowsException() {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        assertThrows(ResponseStatusException.class,()-> userServiceImpl.saveUser(user));
    }

    @Test
    @DisplayName("deleteUser Successfully")
    void deleteUserById() {
        Long id= 1L;
        userServiceImpl.deleteUserById(id);
        verify(userRepository).deleteById(id);
    }

    @Test
    @DisplayName("User Update Successfully")
    void updateUser() {
        User userUpdate =new User(5l,"Jim","Brown","jbrownth@hotmail.com","1981-05-10");
        User userReturned = new User(user.getUserId(),"Jim","Brown","jbrownth@hotmail.com","1981-05-10");
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(userServiceImpl.getUserById(user.getUserId())).thenReturn(user);
        when(userServiceImpl.updateUser(user.getUserId(),userUpdate)).thenReturn(userReturned);
        assertEquals(userReturned,userServiceImpl.updateUser(user.getUserId(),userUpdate));

    }
}