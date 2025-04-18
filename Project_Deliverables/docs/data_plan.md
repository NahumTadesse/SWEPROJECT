# Data Storage Plan

## What we plan on using

- SQLite as our database
- Spring Boot as our framework 
- Spring Data JPA to manage database access
- JDBC driver to connect to the SQLite file


## Why SQLite

- No server setup required
- Keeps all data saved even after the app is closed
- Simple to use and perfect for class projects
- Supports relationships between tables


## What We Will Do

- Connect our Spring Boot app to an embedded SQLite database
- Store data in a file called shoevault.db
- Create entity classes for users, products, orders, payments, etc.
- Use Spring Data JPA to save and retrieve data from the database
- Automatically load starting data (admin user, shipping types, products) when the app starts

## What Will Be Stored

| Table         | Description                                |
|---------------|--------------------------------------------|
| User          | Stores users and admin accounts            |
| Product       | Stores sneaker inventory                   |
| CartItem      | Tracks items in each user's cart           |
| Order         | Stores checkout and shipping information   |
| OrderItem     | Stores which products are in each order    |
| Payment       | Stores payment details and totals          |
| ShippingType  | Stores shipping options and costs          |



## Summary

- Data is stored in a file-based SQLite database
- It is accessed through Spring Boot and Spring Data JPA
- All changes (including puraches) are permanent and will be seen even when it is restarted
