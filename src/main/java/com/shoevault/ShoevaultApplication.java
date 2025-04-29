package com.shoevault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class ShoevaultApplication {

    public static void main(String[] args) {
        //set up database tables if they don't exist
        createUsersTable();
        createProductsTable();
        createCartTable();
        createOrdersTable();
        createOrderItemsTable();
        createPaymentsTable();
        insertSeedProductsIfEmpty(); // Insert demo products if database is empty

        //starting the Spring Boot application
        SpringApplication.run(ShoevaultApplication.class, args);

    }

    // create users table
    private static void createUsersTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:shoedb.db")) {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT NOT NULL UNIQUE, " +
                    "password TEXT NOT NULL, " +
                    "role TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("users table created.");
        } catch (Exception e) {
            System.out.println("failed to create users table: " + e.getMessage());
        }
    }

    // create products table
    private static void createProductsTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:shoedb.db")) {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS products (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "description TEXT, " +
                    "price REAL NOT NULL, " +
                    "sold BOOLEAN DEFAULT 0, " +
                    "imageUrl TEXT)";
            stmt.executeUpdate(sql);
            System.out.println("products table created.");
        } catch (Exception e) {
            System.out.println("failed to create products table: " + e.getMessage());
        }
    }

    // Create items_in_cart table
    private static void createCartTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:shoedb.db")) {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS items_in_cart (" +
                    "cart_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER NOT NULL, " +
                    "product_id INTEGER NOT NULL, " +
                    "FOREIGN KEY(user_id) REFERENCES users(id), " +
                    "FOREIGN KEY(product_id) REFERENCES products(id))";
            stmt.executeUpdate(sql);
            System.out.println("items_in_cart table created.");
        } catch (Exception e) {
            System.out.println("failed to create items_in_cart table: " + e.getMessage());
        }
    }

    // create order table
    private static void createOrdersTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:shoedb.db")) {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `Order` (" +
                    "order_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER, " +
                    "subtotal REAL, " +
                    "tax_amount REAL, " +
                    "shipping_cost REAL, " +
                    "shipping_method TEXT, " +
                    "shipping_address TEXT, " +
                    "phone_number TEXT, " +
                    "grand_total REAL, " +
                    "FOREIGN KEY(user_id) REFERENCES users(id))";
            stmt.executeUpdate(sql);
            System.out.println("order table created.");
        } catch (Exception e) {
            System.out.println("failed to create Order table: " + e.getMessage());
        }
    }

    // create OrderItem table
    private static void createOrderItemsTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:shoedb.db")) {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS OrderItem (" +
                    "order_item_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "order_id INTEGER, " +
                    "product_id INTEGER, " +
                    "FOREIGN KEY(order_id) REFERENCES `Order`(order_id), " +
                    "FOREIGN KEY(product_id) REFERENCES products(id))";
            stmt.executeUpdate(sql);
            System.out.println("OrderItem table created.");
        } catch (Exception e) {
            System.out.println("failed to create OrderItem table: " + e.getMessage());
        }
    }

    //create payment table
    private static void createPaymentsTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:shoedb.db")) {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Payment (" +
                    "payment_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "order_id INTEGER, " +
                    "card_number TEXT, " +
                    "card_exp_month INTEGER, " +
                    "card_exp_year INTEGER, " +
                    "cvv INTEGER, " +
                    "paid_total REAL, " +
                    "FOREIGN KEY(order_id) REFERENCES `Order`(order_id))";
            stmt.executeUpdate(sql);
            System.out.println("Payment table created.");
        } catch (Exception e) {
            System.out.println("failed to create Payment table: " + e.getMessage());
        }
    }

    //insert seed products
    private static void insertSeedProductsIfEmpty() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:shoedb.db")) {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM products");
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Seeding demo products...");

                stmt.executeUpdate("INSERT INTO products (name, description, price, sold, imageUrl) VALUES " +
                        "('Jordan 4 Fire Red', 'Classic Air Jordan style with red, black, and white. Clean colorway and comfortable ride.', 250.00, 0, '/shoe1.png')," +
                        "('Jordan 1 Retro', 'Iconic high-top sneaker that launched a legacy. Timeless red and black design.', 230.00, 0, '/shoe2.png')," +
                        "('Nike Air Mag', 'Futuristic limited-edition shoe from Back to the Future II. Extremely rare.', 20000.00, 0, '/shoe3.png')," +
                        "('Nike P-6000', 'Everyday runner meets streetwear edge. Lightweight and versatile.', 150.00, 0, '/shoe4.png')," +
                        "('Puma Classic', 'Old-school low-top with a sleek, minimalist look. Great for casual outfits.', 100.00, 0, '/shoe5.png')," +
                        "('New Balance', 'Chunky design meets comfort. Bold silhouette and strong arch support.', 135.00, 0, '/shoe6.png')");

                System.out.println("Seed products inserted.");
            } else {
                System.out.println("Products already exist. Skipping seeding.");
            }

        } catch (Exception e) {
            System.out.println("Failed to insert demo products: " + e.getMessage());
        }
    }
}
