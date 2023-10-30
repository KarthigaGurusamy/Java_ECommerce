package com.karthigaecommerce.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {


    private static File userCredentials;

    private static File categoryData;
    private static File productData;

    private static File cartData;
    private static File orderData;

    private static String categoryPath = "";
    private static String productPath = "";
    private static String cartPath = "";

    public static File getCredentialsFile() {
        if (userCredentials == null)
            userCredentials = new File("src/main/java/com/karthigaecommerce/asserts/usercredentials.csv");
        return userCredentials;
    }

    public static File getCategoryData() {
        if (categoryData == null)
            categoryData = new File("src/main/java/com/karthigaecommerce/asserts/categorydata.csv");
        return categoryData;
    }

    public static File getProductData() {
        if (productData == null)
            productData = new File("src/main/java/com/karthigaecommerce/asserts/productdata.csv");
        return productData;
    }

    public static File getCartData() {
        if (cartData == null)
            cartData = new File("src/main/java/com/karthigaecommerce/asserts/userscart.csv");
        return cartData;
    }

    public static File getOrdersData() {
        if (orderData == null)
            orderData = new File("src/main/java/com/karthigaecommerce/asserts/userorder.csv");
        return orderData;
    }

    public static String getCategoryPath() {
        if (categoryData.exists()) {
            categoryPath = "src/main/java/com/karthigaecommerce/asserts/categorydata.csv";
        }
        return categoryPath;
    }

    public static String getProductPath() {
        if (productData.exists()) {
            productPath = "src/main/java/com/karthigaecommerce/asserts/productdata.csv";
        }
        return productPath;
    }

    public static String getCartPath() {

        cartPath = "src/main/java/com/karthigaecommerce/asserts/userscart.csv";
        return cartPath;
    }
}

