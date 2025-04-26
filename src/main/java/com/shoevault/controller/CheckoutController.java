package com.shoevault.controller;

import com.shoevault.model.ItemsInCart;
import com.shoevault.model.Product;
import com.shoevault.repository.CartRepository;
import com.shoevault.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/checkout")
    public String showCheckoutPage() {
        return "checkout"; // Show checkout.html
    }

    @PostMapping("/confirmOrder")
    public String confirmOrder(
            @RequestParam("shippingAddress") String shippingAddress,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("cardNumber") String cardNumber,
            @RequestParam("cardExpMonth") String cardExpMonth,
            @RequestParam("cardExpYear") String cardExpYear,
            @RequestParam("cvv") String cvv,
            @RequestParam("shippingSpeed") String shippingSpeed,
            HttpSession session,
            Model model
    ) {
        Integer userIdInt = (Integer) session.getAttribute("userId");
        Long userId = (userIdInt != null) ? userIdInt.longValue() : null;

        if (userId == null) {
            return "redirect:/login";
        }

        // Save checkout info temporarily in session
        session.setAttribute("shippingAddress", shippingAddress);
        session.setAttribute("phoneNumber", phoneNumber);
        session.setAttribute("cardNumber", cardNumber);
        session.setAttribute("cardExpMonth", cardExpMonth);
        session.setAttribute("cardExpYear", cardExpYear);
        session.setAttribute("cvv", cvv);
        session.setAttribute("shippingSpeed", shippingSpeed);

        // Load cart items
        List<ItemsInCart> cartItems = cartRepository.findByUserId(userId);
        List<Product> products = new ArrayList<>();
        for (ItemsInCart item : cartItems) {
            Product product = productRepository.findById(item.getProductId());
            if (product != null) {
                products.add(product);
            }
        }

        // Calculate totals
        double subtotal = products.stream().mapToDouble(Product::getPrice).sum();
        double tax = subtotal * 0.06;
        double shippingCost = 0;
        if (shippingSpeed.equals("Overnight")) {
            shippingCost = 29;
        } else if (shippingSpeed.equals("3-Day")) {
            shippingCost = 19;
        }
        double grandTotal = subtotal + tax + shippingCost;

        // Round to 2 decimals
        subtotal = round(subtotal);
        tax = round(tax);
        shippingCost = round(shippingCost);
        grandTotal = round(grandTotal);

        // Save prices in session
        session.setAttribute("subtotal", subtotal);
        session.setAttribute("tax", tax);
        session.setAttribute("shippingCost", shippingCost);
        session.setAttribute("grandTotal", grandTotal);

        // Pass data to confirmOrder.html
        model.addAttribute("cartItems", products);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("tax", tax);
        model.addAttribute("shippingCost", shippingCost);
        model.addAttribute("grandTotal", grandTotal);

        return "confirmOrder";
    }

    @PostMapping("/completeOrder")
    public String completeOrder(HttpSession session, Model model) {
        Integer userIdInt = (Integer) session.getAttribute("userId");
        Long userId = (userIdInt != null) ? userIdInt.longValue() : null;

        if (userId == null) {
            return "redirect:/login";
        }

        // Get checkout info from session
        double subtotal = (double) session.getAttribute("subtotal");
        double tax = (double) session.getAttribute("tax");
        double shippingCost = (double) session.getAttribute("shippingCost");
        double grandTotal = (double) session.getAttribute("grandTotal");
        String shippingAddress = (String) session.getAttribute("shippingAddress");
        String phoneNumber = (String) session.getAttribute("phoneNumber");
        String cardNumber = (String) session.getAttribute("cardNumber");
        int cardExpMonth = Integer.parseInt((String) session.getAttribute("cardExpMonth"));
        int cardExpYear = Integer.parseInt((String) session.getAttribute("cardExpYear"));
        int cvv = Integer.parseInt((String) session.getAttribute("cvv"));
        String shippingSpeed = (String) session.getAttribute("shippingSpeed");

        // Insert into Order table
        jdbcTemplate.update(
                "INSERT INTO `Order` (user_id, subtotal, tax_amount, shipping_cost, shipping_method, shipping_address, phone_number, grand_total) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                userId, subtotal, tax, shippingCost, shippingSpeed, shippingAddress, phoneNumber, grandTotal
        );

        Integer orderId = jdbcTemplate.queryForObject("SELECT last_insert_rowid()", Integer.class);

        // Insert each item into OrderItem table
        List<ItemsInCart> cartItems = cartRepository.findByUserId(userId);
        for (ItemsInCart item : cartItems) {
            jdbcTemplate.update(
                    "INSERT INTO OrderItem (order_id, product_id) VALUES (?, ?)",
                    orderId, item.getProductId()
            );
            productRepository.markAsSold(item.getProductId());
        }

        // Insert payment info
        jdbcTemplate.update(
                "INSERT INTO Payment (order_id, card_number, card_exp_month, card_exp_year, cvv, paid_total) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                orderId, cardNumber, cardExpMonth, cardExpYear, cvv, grandTotal
        );

        // Clear checkout info from session
        session.removeAttribute("subtotal");
        session.removeAttribute("tax");
        session.removeAttribute("shippingCost");
        session.removeAttribute("grandTotal");
        session.removeAttribute("shippingAddress");
        session.removeAttribute("phoneNumber");
        session.removeAttribute("cardNumber");
        session.removeAttribute("cardExpMonth");
        session.removeAttribute("cardExpYear");
        session.removeAttribute("cvv");
        session.removeAttribute("shippingSpeed");

        // Clear user's cart
        cartRepository.clearCart(userId);

        // Pass needed attributes to the receipt page
        model.addAttribute("shippingAddress", shippingAddress);
        model.addAttribute("cardLast4", cardNumber.substring(cardNumber.length() - 4));
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("tax", tax);
        model.addAttribute("shippingCost", shippingCost);
        model.addAttribute("grandTotal", grandTotal);

        return "receipt";
    }

    private double round(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
