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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    // using the ProductRepository and CartRepository
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    //show the home page
    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model, @RequestParam(value = "search", required = false) String search) {
        // get the logged-in user from session
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        //redirect to login if not logged in to make sure the didnt just type home into url
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        //pass user object to check if they are a admin or not because this will
        model.addAttribute("user", loggedInUser);

        //get all product IDs already in this user's cart
        Set<Long> productIdsInCart = cartRepository.findAll().stream()
                .filter(item -> item.getUserId() == loggedInUser.getId())
                .map(item -> item.getProductId())
                .collect(Collectors.toSet());

        //count how many items are in the cart
        int cartItemCount = productIdsInCart.size();
        model.addAttribute("cartItemCount", cartItemCount);

        //load products that are NOT sold and NOT already in cart
        List<Product> products = productRepository.findAll().stream()
                .filter(p -> !p.isSold() && !productIdsInCart.contains(p.getId()))
                .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice())) // Sort high to low by price
                .collect(Collectors.toList());

        //if search is not empty filter products by name
        if (search != null && !search.isEmpty()) {
            products = products.stream()
                    .filter(product -> product.getName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        //pass products list to the page
        model.addAttribute("products", products);

        return "home"; // Show home.html
    }
}
