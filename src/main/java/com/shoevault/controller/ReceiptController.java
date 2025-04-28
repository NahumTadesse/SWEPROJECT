package com.shoevault.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class ReceiptController {

    // using JdbcTemplate for database access
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //showing receipt page for a given order ID
    @GetMapping("/receipt/{orderId}")
    public String viewReceipt(@PathVariable("orderId") Long orderId, Model model, HttpSession session) {

        //order details (shipping, subtotal, tax, shipping cost, grand total)
        String orderSql = "SELECT shipping_address, subtotal, tax_amount, shipping_cost, grand_total FROM `Order` WHERE order_id = ?";
        Map<String, Object> order = jdbcTemplate.queryForMap(orderSql, orderId);

        //payment info (card number)
        String paymentSql = "SELECT card_number FROM Payment WHERE order_id = ?";
        Map<String, Object> payment = jdbcTemplate.queryForMap(paymentSql, orderId);

        //last 4 digits of card
        String cardNumber = (String) payment.get("card_number");
        String last4Digits = (cardNumber != null && cardNumber.length() >= 4) ?
                cardNumber.substring(cardNumber.length() - 4) : "----";

        //products included in this order
        String productsSql = "SELECT p.name, p.price FROM products p " +
                "JOIN OrderItem oi ON p.id = oi.product_id " +
                "WHERE oi.order_id = ?";
        List<Map<String, Object>> products = jdbcTemplate.queryForList(productsSql, orderId);

        //pass all receipt info to the view
        model.addAttribute("shippingAddress", order.get("shipping_address"));
        model.addAttribute("subtotal", order.get("subtotal"));
        model.addAttribute("tax", order.get("tax_amount"));
        model.addAttribute("shippingCost", order.get("shipping_cost"));
        model.addAttribute("grandTotal", order.get("grand_total"));
        model.addAttribute("cardLast4", last4Digits);
        model.addAttribute("products", products);
// Show receipt.html
        return "receipt";
    }
}
