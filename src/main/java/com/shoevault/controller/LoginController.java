package com.shoevault.controller;

import com.shoevault.model.User;
import com.shoevault.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    //UserRepository
    @Autowired
    private UserRepository userRepository;

    // show login page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Show login.html
    }

    //handling login
    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model) {
        //find user by username
        User user = userRepository.findByUsername(username);

        //check if user exists and password matches
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Stay on login page if login fails
        }

        //save user info into session
        session.setAttribute("loggedInUser", user); // ✅ Save the full User object
        session.setAttribute("userId", user.getId()); // ✅ Also save userId separately

        return "redirect:/home"; // Redirect to home page after successful login
    }

    //handling logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Clear all session data
        session.invalidate();
        // Redirect back to login page
        return "redirect:/login";
    }
}
