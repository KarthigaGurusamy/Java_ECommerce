package com.karthigaecommerce.models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Cart {
    private int id;
    private User user;

    public Cart(int id, User user, Product product, int count) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Product product;
    private int count;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
