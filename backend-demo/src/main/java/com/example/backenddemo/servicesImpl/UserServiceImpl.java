package com.example.backenddemo.servicesImpl;

import com.example.backenddemo.models.User;
import com.example.backenddemo.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserServiceImpl implements com.example.backenddemo.services.UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        if(userRepository.findById(id).isEmpty())
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        else
            return userRepository.getReferenceById(id);
    }

    @Override
    public User saveUser(User user) {
        if(userRepository.findByEmail(user.getEmail()) == null)
            return userRepository.saveAndFlush(user);
        else
            throw new ResponseStatusException(CONFLICT, "This email already exists");
    }

    @Override
    public void deleteUserById(Long id) {
            userRepository.deleteById(id);
        }
        

    @Override
    public User updateUser(Long id,User user) {
            User existingUser = userRepository.getReferenceById(id);
            BeanUtils.copyProperties(user, existingUser, "userId");
            return userRepository.saveAndFlush(existingUser);
    }
}
