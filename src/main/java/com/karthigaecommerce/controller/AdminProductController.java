package com.karthigaecommerce.controller;

import com.karthigaecommerce.models.Category;
import com.karthigaecommerce.models.Product;
import com.karthigaecommerce.utils.AppException;
import com.karthigaecommerce.utils.StringUtil;
import com.karthigaecommerce.views.AdminProductPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.karthigaecommerce.controller.AdminController.getProductArr;
import static com.karthigaecommerce.controller.HomeController.getCategoryArr;
import static com.karthigaecommerce.utils.AppInput.*;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.FileUtil.getCategoryData;
import static com.karthigaecommerce.utils.FileUtil.getProductData;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AdminProductController {


    private final AdminProductPage adminProductPage;
    private final AdminController adminController;

    public AdminProductController(AdminController adminController) {
        this.adminProductPage = new AdminProductPage();
        this.adminController = adminController;
    }


    public void viewProducts() {

        adminProductPage.viewProducts(getProductArr());
        println(StringUtil.STATIC_CHOICES);
        adminProductPage.productChoices();
        int choice;
        try {
            choice = enterInteger(StringUtil.CHOICE);
            if (choice == 1) {
                AddProduct();
            } else if (choice == 2) {
                EditProduct();
            } else if (choice == 3) {
                DeleteProduct();
            } else if (choice == 4) {
                adminController.adminMenu();
            } else {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException e) {
            invalidException(e);

        }
    }

    private void DeleteProduct() {
    }

    private void EditProduct() {
    }

    private void AddProduct() {
        String categoryName, title, description;
        double price = 0;
        int stocks = 0;
        categoryName = enterString(StringUtil.ENTER_CATEGORY_NAME);
        title = enterString(StringUtil.ENTER_PRODUCT_NAME);
        description = enterString(StringUtil.ENTER_PRODUCT_DESCRIPTION);
        try {
            price = enterDouble(StringUtil.ENTER_PRODUCT_PRICE);
        } catch (AppException e) {
            invalidException(e);
        }
        try {
            stocks = enterInteger(StringUtil.ENTER_PRODUCT_STOCKS);
        } catch (AppException e) {
            invalidException(e);
        }

        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(getProductData(), true);
            int id = (int) (Math.random() * 100);
            csvWriter.append("\n");
            int categoryId = 0;
            for (Category category : AdminController.getCategoryArr()) {
                if (category.getCategoryName() == categoryName) {
                    categoryId = category.getId();
                    break;
                }
            }
            csvWriter.append(id + "," + categoryId + "," + title + "," + description + "," + price + "," + stocks);
            csvWriter.flush();
            csvWriter.close();
            println(StringUtil.PRODUCT_SUCCESSFUL);
            viewProducts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void invalidException(AppException e) {
        println(e.getMessage());
        adminProductPage.productChoices();
    }
}
