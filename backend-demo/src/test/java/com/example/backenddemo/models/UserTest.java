package com.example.backenddemo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("User class Tests")
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
        () -> assertEquals(null, user.getUserId()),
                    () -> assertEquals(null, user.getDateOfBirth()),
                    () -> assertEquals(null, user.getEmail()),
                    () -> assertEquals(null, user.getFirstName()),
                    () -> assertEquals(null, user.getLastName())
            );
        }

        @Test
        @DisplayName("Non empty user with parameters constructor successfully")
        void createNonEmptyUserWithParametersConstructor() {
            user = new User(1l,"John","Smith","johnsmith@gmail.com","1993-02-22");
            assertAll(
                    () -> assertEquals(1L, user.getUserId()),
                    () -> assertEquals("1993-02-22", user.getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                    () -> assertEquals("johnsmith@gmail.com", user.getEmail()),
                    () -> assertEquals("John", user.getFirstName()),
                    () -> assertEquals("Smith", user.getLastName())
            );

        }

        @Test
        @DisplayName("Non empty user with default constructor successfully")
        void createNonEmptyUser() {
            user.setUserId(1L);
            user.setDateOfBirth("1993-02-22");
            user.setEmail("johnsmith@gmail.com");
            user.setFirstName("John");
            user.setLastName("Smith");
            assertAll(
                    () -> assertEquals(1L, user.getUserId()),
                    () -> assertEquals("1993-02-22", user.getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                    () -> assertEquals("johnsmith@gmail.com", user.getEmail()),
                    () -> assertEquals("John", user.getFirstName()),
                    () -> assertEquals("Smith", user.getLastName())
            );
        }
    }
}