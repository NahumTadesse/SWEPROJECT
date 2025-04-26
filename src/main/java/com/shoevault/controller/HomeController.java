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

        model.addAttribute("user", loggedInUser); // ✅ Pass user object

        // Get cart items for this user
        Set<Long> productIdsInCart = cartRepository.findAll().stream()
                .filter(item -> item.getUserId() == loggedInUser.getId())
                .map(item -> item.getProductId())
                .collect(Collectors.toSet());

        int cartItemCount = productIdsInCart.size(); // ✅ Count how many items are in cart
        model.addAttribute("cartItemCount", cartItemCount);

        // Load products that are NOT sold and NOT already added to cart
        List<Product> products = productRepository.findAll().stream()
                .filter(p -> !p.isSold() && !productIdsInCart.contains(p.getId()))
                .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                .collect(Collectors.toList());

        model.addAttribute("products", products);
        return "home";
    }
}
