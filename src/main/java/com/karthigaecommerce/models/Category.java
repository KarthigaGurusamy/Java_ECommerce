package com.karthigaecommerce.models;

import java.sql.Timestamp;

public class Category {

    private int id;
    private String categoryName;

    public int getId() {
        return id;
    }

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


}
