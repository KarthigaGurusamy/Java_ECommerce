package com.karthigaecommerce.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int id;
    private Date date;

    private ArrayList<Cart> carts;

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


    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }
}
