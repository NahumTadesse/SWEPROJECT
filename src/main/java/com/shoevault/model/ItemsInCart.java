package com.shoevault.model;


public class ItemsInCart {
    // unique ID for cart entry
    private long cartId;
    // userID of who added the item
    private long userId;
    // product of the shoe that was added
    private long productId;
    // getter for cartId
    public long getCartId() {
        return cartId;
    }
    // setter for cartId
    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
    // getter for userId
    public long getUserId() {
        return userId;
    }
    // setter for userId
    public void setUserId(long userId) {
        this.userId = userId;
    }

    // getter for productId
    public long getProductId() {
        return productId;
    }

    // setter for productId
    public void setProductId(long productId) {
        this.productId = productId;
    }
}
