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

    // ProductRepository to save products
    @Autowired
    private ProductRepository productRepository;

    // show the add inventory page
    @GetMapping("/addInventory")
    public String showAddInventoryPage() {
        return "addInventory";
    }

    // handle the submission from the admin to add a new product
    @PostMapping("/addInventory")
    public String addInventory(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile image,
            Model model) {

        try {
            // set upload directory path under(static/uploads folder)
            String uploadDirPath = new File("src/main/resources/static/uploads").getAbsolutePath();
            File uploadDir = new File(uploadDirPath);

            // creating an uploads folder in case it get deleted for whatever reason
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String imageUrl = null;

            // if an image was uploaded the following will run
            if (!image.isEmpty()) {
                String originalFilename = image.getOriginalFilename();
                String timestamp = String.valueOf(System.currentTimeMillis());
                String imageName = timestamp + "_" + originalFilename;

                //saving the uploaded image file
                Path destinationPath = Paths.get(uploadDirPath, imageName);
                Files.copy(image.getInputStream(), destinationPath);

                //set image URL path for browser access
                imageUrl = "/uploads/" + imageName;
            }

            // create a new product and save it
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setImageUrl(imageUrl);
            product.setSold(false); // New product is marked as unsold

            // save the product to the database
            productRepository.save(product);

            // the following will show the product has been succesfully added
            model.addAttribute("successMessage", "✅ Product added successfully!");

        } catch (IOException e) {
            // the following will show the product has not been succesfully added
            model.addAttribute("errorMessage", "❌ Failed to upload image or save product: " + e.getMessage());
            e.printStackTrace();
        }

        //returns to addInventory page
        return "addInventory";
    }
}
