package com.shoevault.controller;

import com.shoevault.model.ItemsInCart;
import com.shoevault.model.Product;
import com.shoevault.model.User;
import com.shoevault.repository.CartRepository;
import com.shoevault.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<ItemsInCart> items = cartRepository.findByUserId(user.getId());

        List<Product> cartItems = items.stream()
                .map(item -> productRepository.findById(item.getProductId()))
                .collect(Collectors.toList());

        double totalPrice = cartItems.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") long productId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        ItemsInCart item = new ItemsInCart();
        item.setUserId(user.getId());
        item.setProductId(productId);

        int result = cartRepository.save(item);
        if (result > 0) {
            productRepository.markAsSold(productId);
        }

        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam("productId") long productId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        cartRepository.deleteByProductId(productId);
        productRepository.markAsAvailable(productId);
        return "redirect:/cart";
    }
}
