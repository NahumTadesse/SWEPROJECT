package com.shoevault.model;

// Represents a user account in ShoeVault
public class User {
    // unique user ID
    private int id;
    // user's username
    private String username;
    //user's password
    private String password;
    // user's role (user or admin)
    private String role;


    public User() {}

    //constructor with all fields
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // getter for id
    public int getId() { return id; }

    //setter for id
    public void setId(int id) { this.id = id; }

        // getter for username
    public String getUsername() { return username; }

     // setter for username
    public void setUsername(String username) { this.username = username; }

    //getter for password
    public String getPassword() { return password; }

        // setter for password
    public void setPassword(String password) { this.password = password; }

    // getter for role
    public String getRole() { return role; }

        // setter for role
    public void setRole(String role) { this.role = role; }

    //check if the user is an Admin
    public boolean isAdmin() {
        return role != null && role.equalsIgnoreCase("ADMIN");
    }

    // check if the user is a regular User
    public boolean isUser() {
        return role != null && role.equalsIgnoreCase("USER");
    }
}
