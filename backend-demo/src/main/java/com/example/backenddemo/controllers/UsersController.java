package com.example.backenddemo.controllers;

import com.example.backenddemo.models.User;
import com.example.backenddemo.servicesImpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UserServiceImpl userService;

    public UsersController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> list() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
       return userService.saveUser(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

}