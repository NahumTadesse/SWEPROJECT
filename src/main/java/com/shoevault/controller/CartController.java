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

    //CartRepository to manage items in the users cart
    @Autowired
    private CartRepository cartRepository;

    //ProductRepository to manage product status
    @Autowired
    private ProductRepository productRepository;

    //view cart page
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        // this works to make sure after a user is logged out you cant just type in cart into the url to get to the cart
        if (user == null) {
            return "redirect:/login";
        }

        //get all cart items for the user
        List<ItemsInCart> items = cartRepository.findByUserId(user.getId());

        //getting the product details for each item
        List<Product> cartItems = items.stream()
                .map(item -> productRepository.findById(item.getProductId()))
                .collect(Collectors.toList());

        //calculating the total price
        double totalPrice = cartItems.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        //pass cart items and total price to the view
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }

    // adding product to cart
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") long productId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        // this works to make sure after a user is logged out you cant just type in cart into the url to get to the cart
        if (user == null) {
            return "redirect:/login";
        }

        // create a new cart item
        ItemsInCart item = new ItemsInCart();
        item.setUserId(user.getId());
        item.setProductId(productId);

        // save the cart item and mark the product as sold
        int result = cartRepository.save(item);
        if (result > 0) {
            productRepository.markAsSold(productId);
        }

        // redirect back to cart
        return "redirect:/cart";
    }

    // remove product from cart
    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam("productId") long productId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        // Redirect to login if user not logged in
        if (user == null) {
            return "redirect:/login";
        }

        // delete the item from cart and mark the product as available
        cartRepository.deleteByProductId(productId);
        productRepository.markAsAvailable(productId);

        // redirect back to cart
        return "redirect:/cart";
    }
}
