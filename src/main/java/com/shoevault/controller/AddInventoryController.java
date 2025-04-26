package com.shoevault.controller;

import com.shoevault.model.Product;
import com.shoevault.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class AddInventoryController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/addInventory")
    public String showAddInventoryPage() {
        return "addInventory";
    }

    @PostMapping("/addInventory")
    public String addInventory(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile image,
            Model model) {

        try {
            // ✅ Save images inside src/main/resources/static/uploads/
            String uploadDirPath = new File("src/main/resources/static/uploads").getAbsolutePath();
            File uploadDir = new File(uploadDirPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Create uploads folder if it doesn't exist
            }

            String imageUrl = null;
            if (!image.isEmpty()) {
                String originalFilename = image.getOriginalFilename();
                String timestamp = String.valueOf(System.currentTimeMillis());
                String imageName = timestamp + "_" + originalFilename;

                // ✅ Save the uploaded file
                Path destinationPath = Paths.get(uploadDirPath, imageName);
                Files.copy(image.getInputStream(), destinationPath);

                // ✅ Save browser-accessible path in database
                imageUrl = "/uploads/" + imageName;
            }

            // ✅ Save the product into database
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setImageUrl(imageUrl);
            product.setSold(false); // New product is unsold

            productRepository.save(product);

            model.addAttribute("successMessage", "✅ Product added successfully!");

        } catch (IOException e) {
            model.addAttribute("errorMessage", "❌ Failed to upload image or save product: " + e.getMessage());
            e.printStackTrace();
        }

        return "addInventory";
    }
}
