package com.example.backenddemo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    private  User user;

    @BeforeEach
    void init() {
        user = new User();}

    @Nested
    @DisplayName("Create ")
    class UserCreate {

        @Test
        @DisplayName("empty user successfully")
        void createEmptyUser() {
            assertAll(
        () -> assertEquals(null, user.getUser_id()),
                    () -> assertEquals(null, user.getDate_of_birth()),
                    () -> assertEquals(null, user.getEmail()),
                    () -> assertEquals(null, user.getFirst_name()),
                    () -> assertEquals(null, user.getLast_name())
            );
        }

        @Test
        @DisplayName("Non empty user with parameters constructor successfully")
        void createNonEmptyUserWithParametersConstructor() {
            user = new User(1l,"John","Smith","johnsmith@gmail.com","1993-02-22");
            assertAll(
                    () -> assertEquals(1L, user.getUser_id()),
                    () -> assertEquals("1993-02-22", user.getDate_of_birth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                    () -> assertEquals("johnsmith@gmail.com", user.getEmail()),
                    () -> assertEquals("John", user.getFirst_name()),
                    () -> assertEquals("Smith", user.getLast_name())
            );

        }

        @Test
        @DisplayName("Non empty user with default constructor successfully")
        void createNonEmptyUser() {
            user.setUser_id(1L);
            user.setDate_of_birth("1993-02-22");
            user.setEmail("johnsmith@gmail.com");
            user.setFirst_name("John");
            user.setLast_name("Smith");
            assertAll(
                    () -> assertEquals(1L, user.getUser_id()),
                    () -> assertEquals("1993-02-22", user.getDate_of_birth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                    () -> assertEquals("johnsmith@gmail.com", user.getEmail()),
                    () -> assertEquals("John", user.getFirst_name()),
                    () -> assertEquals("Smith", user.getLast_name())
            );
        }
    }
}