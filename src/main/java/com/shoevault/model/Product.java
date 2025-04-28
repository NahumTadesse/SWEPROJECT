package com.shoevault.model;

// Represents a product in the store
public class Product {
    // unique ID for the product
    private long id;
    // product name
    private String name;
    // product description
    private String description;
    // product price
    private double price;
    // whether the product is sold
    private boolean sold;
    // URL path to product image
    private String imageUrl;

    // getter for product ID
    public long getId() {
        return id;
    }

    //setter for product ID
    public void setId(long id) {
        this.id = id;
    }

    //getter for product name
    public String getName() {
        return name;
    }

    //setter for product name
    public void setName(String name) {
        this.name = name;
    }

    //getter for product description
    public String getDescription() {
        return description;
    }

    //setter for product description
    public void setDescription(String description) {
        this.description = description;
    }

    //getter for product price
    public double getPrice() {
        return price;
    }

    //setter for product price
    public void setPrice(double price) {
        this.price = price;
    }

    // getter for sold status
    public boolean isSold() {
        return sold;
    }

    //setter for sold status
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    //getter for product image URL
    public String getImageUrl() {
        return imageUrl;
    }

    //setter for product image URL
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
