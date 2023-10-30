package com.karthigaecommerce.views;

import com.karthigaecommerce.models.Product;
import com.karthigaecommerce.utils.StringUtil;

import java.util.ArrayList;

import static com.karthigaecommerce.utils.AppOutput.println;

public class AdminProductPage {
    public void viewProducts(ArrayList<Product> productData) {
        println(StringUtil.PRODUCT_MESSAGE);
        for (Product data : productData) {

            println(data.getId() + ". " + data.getTitle() + ", " + data.getDescription() + ", ₹." + data.getPrice());

        }

    }

    public void productChoices() {
        println(StringUtil.ADMIN_PRODUCT_CHOICE);
    }
}
