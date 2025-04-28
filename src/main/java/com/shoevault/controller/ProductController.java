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

    // using the ProductRepository and CartRepository
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemsInCartRepository cartRepository;

    //show individual product page
    @GetMapping("/product/{id}")
    public String showProductPage(@PathVariable("id") long id, Model model) {
        // find product by ID
        Product product = productRepository.findById(id);

        //passing product to the view
        model.addAttribute("product", product);

        return "product"; // Show product.html
    }

    //adding a product to cart
    @PostMapping("/product/{id}/add-to-cart")
    public String addToCart(@PathVariable("id") long productId, HttpSession session) {
        //get logged-in user from session
        User user = (User) session.getAttribute("loggedInUser");

        //redirect to login if not logged in
        if (user == null) {
            return "redirect:/login";
        }

        //create a new cart item
        ItemsInCart item = new ItemsInCart();
        item.setUserId(user.getId());
        item.setProductId(productId);

        //save the item to cart
        cartRepository.save(item);

        //mark product as sold so it can be seen anymore on the homepage
        productRepository.markAsSold(productId);

        // redirect back to home page
        return "redirect:/home";
    }
}
