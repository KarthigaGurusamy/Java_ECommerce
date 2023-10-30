package com.karthigaecommerce.views;

import com.karthigaecommerce.models.Product;
import com.karthigaecommerce.utils.StringUtil;

import java.util.ArrayList;

import static com.karthigaecommerce.controller.ProductController.getProductArr;
import static com.karthigaecommerce.utils.AppOutput.println;

public class ProductPage {
    public void printProductMenu(int choice, ArrayList<Product> productData) {
        println(StringUtil.PRODUCT_MESSAGE);
        for (Product data : productData) {
            if (data.getCategory().getId() == choice) {
                println(data.getId() + ". " + data.getTitle() + ", " + data.getDescription() + ", ₹." + data.getPrice());
            }
        }
    }

    public void printAllProducts(ArrayList<Product> productData) {
        println(StringUtil.PRODUCT_MESSAGE);
        for (Product data : productData) {

            println(data.getId() + ". " + data.getTitle() + ", " + data.getDescription() + ", ₹." + data.getPrice());

        }
    }
}
