package com.example.backenddemo.controllers;

import com.example.backenddemo.repositories.UserRepositoryDouble;
import com.example.backenddemo.repositories.UserRepositoryDoubleImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


class UsersControllerTest {

    private UserRepositoryDouble userRepositoryDouble;

    @BeforeEach
    public void init(){
        userRepositoryDouble = new UserRepositoryDoubleImpl();
    }


    @Test
    @DisplayName("GET request return All user")
    void getAllUsers(){
        assertAll(
                ()-> assertEquals(null,userRepositoryDouble.findAll().get(0).getUser_id()),
                ()->assertEquals(null,userRepositoryDouble.findAll().get(0).getFirst_name()),
                ()->assertEquals(null,userRepositoryDouble.findAll().get(0).getLast_name()),
                ()->assertEquals(null,userRepositoryDouble.findAll().get(0).getEmail()),
                ()->assertEquals(null,userRepositoryDouble.findAll().get(0).getDate_of_birth())
        );

        assertAll(
                ()-> assertEquals(1L,userRepositoryDouble.findAll().get(1).getUser_id()),
                ()->assertEquals("John",userRepositoryDouble.findAll().get(1).getFirst_name()),
                ()->assertEquals("Smith",userRepositoryDouble.findAll().get(1).getLast_name()),
                ()->assertEquals("jsmith@gmail.com",userRepositoryDouble.findAll().get(1).getEmail()),
                ()->assertEquals("1993-04-12",userRepositoryDouble.findAll().get(1).getDate_of_birth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        );

        assertAll(
                ()-> assertEquals(2L,userRepositoryDouble.findAll().get(2).getUser_id()),
                ()->assertEquals("Peter",userRepositoryDouble.findAll().get(2).getFirst_name()),
                ()->assertEquals("Lin",userRepositoryDouble.findAll().get(2).getLast_name()),
                ()->assertEquals("linlin@gmail.com",userRepositoryDouble.findAll().get(2).getEmail()),
                ()->assertEquals("1983-09-12",userRepositoryDouble.findAll().get(2).getDate_of_birth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        );
    }
    @Test
    @DisplayName("GET request return single user by id")
    @Disabled
    void getSingleUserById(){
        assertEquals(userRepositoryDouble.findById(1L),userRepositoryDouble.findAll().get(0));
    }

}