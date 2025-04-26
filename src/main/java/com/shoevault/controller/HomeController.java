package com.shoevault.controller;

import com.shoevault.model.Product;
import com.shoevault.model.User;
import com.shoevault.repository.CartRepository;
import com.shoevault.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", loggedInUser.getUsername());

        // Get IDs of products in the cart
        Set<Long> productIdsInCart = cartRepository.findAll().stream()
                .map(item -> item.getProductId())
                .collect(Collectors.toSet());

        // Filter out sold products and products in cart
        List<Product> products = productRepository.findAll().stream()
                .filter(p -> !p.isSold() && !productIdsInCart.contains(p.getId()))
                .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                .collect(Collectors.toList());

        model.addAttribute("products", products);
        return "home";
    }
}
