package com.shoevault.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
public class SalesReportController {

    // using JdbcTemplate for database access
    @Autowired
    private JdbcTemplate jdbcTemplate;

        //  show sales report page
    @GetMapping("/salesReport")
    public String showSalesReport(Model model) {
        //all sold products with order and user info
        String sql = "SELECT p.id AS product_id, p.name, p.price, u.username, o.order_id " +
                "FROM products p " +
                "JOIN OrderItem oi ON p.id = oi.product_id " +
                "JOIN `Order` o ON oi.order_id = o.order_id " +
                "JOIN users u ON o.user_id = u.id " +
                "WHERE p.sold = 1";

        List<Map<String, Object>> salesData = jdbcTemplate.queryForList(sql);

        // sales data to salesReport.html
        model.addAttribute("salesData", salesData);

        return "salesReport";
    }

    // exporting  sales report as a CSV file
    @GetMapping("/exportSalesCsv")
    public void exportSalesReport(HttpServletResponse response) throws IOException {
        // ✅ Load sold product data again for export
        String sql = "SELECT p.name, p.price, u.username, o.order_id " +
                "FROM products p " +
                "JOIN OrderItem oi ON p.id = oi.product_id " +
                "JOIN `Order` o ON oi.order_id = o.order_id " +
                "JOIN users u ON o.user_id = u.id " +
                "WHERE p.sold = 1";

        List<Map<String, Object>> salesData = jdbcTemplate.queryForList(sql);

        //HTTP response for CSV download
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"sales_report.csv\"");

        //writing CSV content
        PrintWriter writer = response.getWriter();
        writer.println("Product Name,Price,Username,Order ID");

        for (Map<String, Object> row : salesData) {
            writer.println(
                    row.get("name") + "," +
                            row.get("price") + "," +
                            row.get("username") + "," +
                            row.get("order_id")
            );
        }

        writer.flush();
        writer.close();
    }
}
