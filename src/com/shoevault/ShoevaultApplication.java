package com.shoevault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@SpringBootApplication
public class ShoevaultApplication {

    public static void main(String[] args) {
        // Step 1: Create users table if it doesn't exist
        createUsersTable();

        // Step 2: Start Spring Boot app
        SpringApplication.run(ShoevaultApplication.class, args);

        // Step 3: Open browser
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(new URI("http://localhost:8081"));
                }
            }
        } catch (Exception e) {
            System.out.println("Could not open browser automatically: " + e.getMessage());
        }
    }

    private static void createUsersTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:shoedb.db")) {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT NOT NULL UNIQUE, " +
                    "password TEXT NOT NULL, " +
                    "role TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("✅ 'users' table created.");
        } catch (Exception e) {
            System.out.println("❌ Failed to create users table: " + e.getMessage());
        }
    }
}
