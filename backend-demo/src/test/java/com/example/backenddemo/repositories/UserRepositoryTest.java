package com.example.backenddemo.repositories;

import com.example.backenddemo.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("UserRepository Test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository= mock(UserRepository.class);

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Find User by email Successfully")
    void shouldFindUserByEmail(){
        User user1 = new User(1l,"John","Smith","johnsmith@gmail.com","1993-02-22");
        when(userRepository.findByEmail("johnsmith@gmail.com"))
                .thenReturn(user1 );
        assertAll(
                ()->assertEquals(user1,userRepository.findByEmail("johnsmith@gmail.com")),
        ()->assertNotEquals(user1,userRepository.findByEmail("notequals@gmail.com")),
        ()->assertNotNull(userRepository.findByEmail("johnsmith@gmail.com"))
        );
    }

    @Test
    @DisplayName("Find by email is null check")
    void nullUserByEmail(){
        when(userRepository.findByEmail("johnsmith@gmail.com"))
                .thenReturn(null);
        assertNull(userRepository.findByEmail("johnsmith@gmail.com"));
    }

}