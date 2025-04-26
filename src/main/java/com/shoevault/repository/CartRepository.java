package com.shoevault.repository;

import com.shoevault.model.ItemsInCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(ItemsInCart item) {
        String checkSql = "SELECT COUNT(*) FROM items_in_cart WHERE product_id = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, item.getProductId());

        if (count != null && count > 0) {
            // Product already in cart, don't insert again
            return 0;
        }

        String sql = "INSERT INTO items_in_cart (user_id, product_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, item.getUserId(), item.getProductId());
    }

    public List<ItemsInCart> findByUserId(long userId) {
        String sql = "SELECT * FROM items_in_cart WHERE user_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs), userId);
    }

    private ItemsInCart mapRow(ResultSet rs) throws SQLException {
        ItemsInCart item = new ItemsInCart();
        item.setCartId(rs.getLong("cart_id"));
        item.setUserId(rs.getLong("user_id"));
        item.setProductId(rs.getLong("product_id"));
        return item;
    }
    public List<ItemsInCart> findAll() {
        String sql = "SELECT * FROM items_in_cart";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs));
    }
    public int deleteByProductId(long productId) {
        String sql = "DELETE FROM items_in_cart WHERE product_id = ?";
        return jdbcTemplate.update(sql, productId);
    }
    public void clearCart(long userId) {
        String sql = "DELETE FROM items_in_cart WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }



}
