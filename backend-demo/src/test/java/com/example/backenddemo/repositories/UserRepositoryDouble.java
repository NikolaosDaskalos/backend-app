package com.example.backenddemo.repositories;

import com.example.backenddemo.models.User;

import java.util.List;

public interface UserRepositoryDouble {

    List<User> findAll();

    User findById(Long id);
}
