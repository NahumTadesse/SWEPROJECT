package com.shoevault.service;

import com.shoevault.model.User;
import com.shoevault.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(String username, String password, String role) {
        if (userRepository.findByUsername(username) != null) {
            return false; // Username already exists
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // You can hash it here later
        user.setRole(role.toUpperCase());

        userRepository.save(user);
        return true;
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Login failed
    }
}
