package com.example.backenddemo.controllers;

import com.example.backenddemo.models.User;
import com.example.backenddemo.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Long id) {
        if(userRepository.findById(id).isEmpty())
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        else
            return userRepository.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user){
        if(userRepository.findByEmail(user.getEmail()) == null)
            return userRepository.saveAndFlush(user);
        else
            throw new ResponseStatusException(CONFLICT, "This email already exists");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userRepository.getReferenceById(id);
        BeanUtils.copyProperties(user, existingUser, "user_id");
        return userRepository.saveAndFlush(existingUser);
    }

}