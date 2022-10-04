package com.example.backenddemo.controllers;

import com.example.backenddemo.models.User;
import com.example.backenddemo.servicesImpl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Users Controller Unit Tests")
class UsersControllerUnitTest {
   private UserServiceImpl userService = mock(UserServiceImpl.class);
   private UsersController usersController = new UsersController(userService);
   private User mockUser = new User(1L,"John","Smith","johnsmith@gmail.com","1993-02-22");


    @Test
    @DisplayName("Get All Users Successfully")
    void getUsersListTest() {
     User mockUser2 = new User(2L,"Jim","Brown","jbrownth@hotmail.com","1981-05-10");
     when(userService.getAllUsers()).thenReturn(Arrays.asList(mockUser,mockUser2));
     assertAll(
             ()->assertNotNull(usersController.list()),
             ()->assertSame(userService.getAllUsers(),usersController.list())
     );
    }

    @Test
    @DisplayName("Get User By Id Successfully")
    void getUserByIdTest() {
     when(userService.getUserById(1L)).thenReturn(mockUser);
     assertAll(
             ()->assertNotNull(usersController.get(1L)),
             ()->assertSame(userService.getUserById(1L),usersController.get(1L))
     );
    }

    @Test
    @DisplayName("Create User Successfully")
    void createUserTest() {
     User updateUser = new User(2L,"Jim","Brown","jbrownth@hotmail.com","1981-05-10");
     when(userService.saveUser(mockUser)).thenReturn(mockUser);
     assertAll(
             ()->assertNotNull(usersController.create(mockUser)),
             ()->assertSame(userService.saveUser(mockUser),usersController.create(mockUser))
     );
    }

    @Test
    @DisplayName("Delete User Successfully")
    void deleteUserTest() {
     usersController.delete(1L);
     verify(userService,times(1)).deleteUserById(1L);
    }

    @Test
    @DisplayName("Update User Successfully")
    void updateUserTest() {
     User updateUser = new User(2L,"Jim","Brown","jbrownth@hotmail.com","1981-05-10");
     when(userService.updateUser(1L,updateUser)).thenReturn(mockUser);
     assertAll(
             ()->assertNotNull(usersController.update(1L,updateUser)),
             ()->assertSame(userService.updateUser(1L,updateUser),usersController.update(1L,updateUser))
     );

    }
}