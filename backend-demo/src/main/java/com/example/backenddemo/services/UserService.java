package com.example.backenddemo.services;

import com.example.backenddemo.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User car);
    void deleteUserById(Long id);
    User updateUser(Long id,User car);
}

