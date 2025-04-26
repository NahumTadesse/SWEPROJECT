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

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Show login.html
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }

        session.setAttribute("loggedInUser", user); // ✅ Save the full User object
        session.setAttribute("userId", user.getId()); // ✅ Also save userId separately

        return "redirect:/home"; // Go to home page after login
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear all session data
        return "redirect:/login"; // Go back to login page
    }
}
