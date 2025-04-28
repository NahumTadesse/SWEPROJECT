package com.shoevault.repository;

import com.shoevault.model.ItemsInCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemsInCartRepository {

    //  using JdbcTemplate to run SQL commands
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // save tem into the items_in_cart table
    public int save(ItemsInCart item) {
        String sql = "INSERT INTO items_in_cart (user_id, product_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, item.getUserId(), item.getProductId());
    }
}
