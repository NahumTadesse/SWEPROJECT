package com.shoevault.service;

import com.shoevault.model.User;
import com.shoevault.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        username = username.trim();
        password = password.trim();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("No user found for: " + username);
            return false;
        }

        System.out.println("DB Password: [" + user.getPassword() + "]");
        System.out.println("Input Password: [" + password + "]");

        boolean match = user.getPassword().equals(password);
        if (!match) {
            System.out.println("Password mismatch");
        }

        return match;
    }

    public boolean isAdmin(String username) {
        username = username.trim();
        User user = userRepository.findByUsername(username);
        return user != null && user.isAdmin();
    }
}
