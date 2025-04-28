package com.shoevault.repository;

import com.shoevault.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    // using JdbcTemplate to run SQL queries
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //save a new product into the products table
    public int save(Product product) {
        String sql = "INSERT INTO products (name, description, price, sold, imageUrl) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.isSold() ? 1 : 0, // Store sold as 1 or 0
                product.getImageUrl()
        );
    }

    //all products that are not sold
    public List<Product> findAll() {
        String sql = "SELECT * FROM products WHERE sold = 0";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    //find a product by ID
    public Product findById(long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{id}, new ProductRowMapper());
        return products.isEmpty() ? null : products.get(0);
    }

    // RowMapper to convert SQL result into a product object
    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));
            product.setSold(rs.getBoolean("sold"));
            product.setImageUrl(rs.getString("imageUrl"));
            return product;
        }
    }

    // mark a product as sold
    public void markAsSold(long productId) {
        String sql = "UPDATE products SET sold = 1 WHERE id = ?";
        jdbcTemplate.update(sql, productId);
    }

    // mark a product as available
    public void markAsAvailable(long productId) {
        String sql = "UPDATE products SET sold = 0 WHERE id = ?";
        jdbcTemplate.update(sql, productId);
    }
}
