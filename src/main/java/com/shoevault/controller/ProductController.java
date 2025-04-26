package com.shoevault.controller;

import com.shoevault.model.Product;
import com.shoevault.model.ItemsInCart;
import com.shoevault.model.User;
import com.shoevault.repository.ProductRepository;
import com.shoevault.repository.ItemsInCartRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemsInCartRepository cartRepository;

    @GetMapping("/product/{id}")
    public String showProductPage(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "product";
    }

    @PostMapping("/product/{id}/add-to-cart")
    public String addToCart(@PathVariable("id") long productId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        ItemsInCart item = new ItemsInCart();
        item.setUserId(user.getId());
        item.setProductId(productId);
        cartRepository.save(item);

        productRepository.markAsSold(productId);  // Hide from home

        return "redirect:/home";
    }
}
