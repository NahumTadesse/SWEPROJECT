package com.shoevault.service;

import com.shoevault.model.User;
import com.shoevault.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    // using UserRepository
    @Autowired
    private UserRepository userRepository;
    //registering a new user
    public boolean registerUser(String username, String password, String role) {
        //check if username already exists
        if (userRepository.findByUsername(username) != null) {
            return false; // Username is already taken
        }
        //create a new user and save it
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role.toUpperCase());

        userRepository.save(user);
        // registration successful
        return true;
    }

    // authenticate a user during login
    public User loginUser(String username, String password) {
        // find user by username
        User user = userRepository.findByUsername(username);

        // check if password matches
        if (user != null && user.getPassword().equals(password)) {
            //login successful
            return user;
        }
         // login failed
        return null;
    }
}
