package com.shoevault.controller;

import com.shoevault.model.User;
import com.shoevault.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    //using UserRepository
    @Autowired
    private UserRepository userRepository;

    // show registration form page
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // pass an empty user object to the form
        model.addAttribute("user", new User());
        return "register"; // Show register.html
    }

    //handling registration
    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("role") String role,
                               Model model) {

        //checking if username already exists it it does login will fail
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists");
            return "register"; // Stay on registration page if username is taken
        }

        //creating new user and save it
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role.toUpperCase()); // Always save role as uppercase (USER or ADMIN)

        userRepository.save(user);
        // if the registration is successful it will redirect to login page
        return "redirect:/login";
    }
}
