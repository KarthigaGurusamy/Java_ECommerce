package com.karthigaecommerce.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int id;
    private User user;
    private Date date;

    private Product product;

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order(int id, User user, Date date, Product product) {
        this.id = id;
        this.date = date;
        this.product=product;
        this.user=user;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
