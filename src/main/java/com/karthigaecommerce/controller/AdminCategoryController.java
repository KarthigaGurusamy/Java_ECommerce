package com.karthigaecommerce.controller;

import com.karthigaecommerce.models.Category;
import com.karthigaecommerce.models.Product;
import com.karthigaecommerce.models.Role;
import com.karthigaecommerce.utils.AppException;
import com.karthigaecommerce.utils.StringUtil;
import com.karthigaecommerce.views.AdminCategoryPage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static com.karthigaecommerce.controller.AdminController.getCategoryArr;
import static com.karthigaecommerce.controller.AdminController.getProductArr;
import static com.karthigaecommerce.utils.AppInput.enterInteger;
import static com.karthigaecommerce.utils.AppInput.enterString;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.FileUtil.*;

public class AdminCategoryController {

    private final AdminCategoryPage adminCategoryPage;

    private final AdminController adminController;

    public AdminCategoryController(AdminController adminController) {
        this.adminCategoryPage = new AdminCategoryPage();
        this.adminController = adminController;
    }

    public void viewCategories(ArrayList<Category> categoryData) {
        adminCategoryPage.viewCategories(categoryData);
        println(StringUtil.STATIC_CHOICES);
        adminCategoryPage.categoryChoices();
        int choice;
        try {
            choice = enterInteger(StringUtil.CHOICE);
            if (choice == 1) {
                AddCategory();
            } else if (choice == 2) {
                DeleteCategory();
            } else if (choice == 3) {
                adminController.adminMenu();
            } else {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }

        } catch (AppException e) {
            invalidException(e);

        }
    }

    private void DeleteCategory() {
        String categoryName;
        categoryName = enterString(StringUtil.ENTER_CATEGORY_NAME);
        ArrayList<Category> categoryArr = getCategoryArr();
        ArrayList<Product> productArr = getProductArr();
        categoryArr.removeIf(category -> category.getCategoryName().equals(categoryName));
        productArr.removeIf(product -> product.getCategory().getCategoryName().equals(categoryName));
        writeCategoryToFile(categoryArr);
        writeProductToFile(productArr);
        println(StringUtil.CATEGORY_DELETED_MESSAGE);
        adminController.adminMenu();
    }

    private void writeProductToFile(ArrayList<Product> productArr) {
        String path = getProductPath();
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(getProductData(), true);
            csvWriter.append("id" + "," + "categoryId" + "," + "title" + "," + "description" + "," + "price" + "," + "stocks");
            csvWriter.flush();
            for (Product product : productArr) {
                csvWriter.append("\n");
                csvWriter.append(product.getId() + "," + product.getCategory().getId() + "," + product.getTitle() + "," + product.getDescription() + "," + product.getPrice() + "," + product.getStocks());
                csvWriter.flush();

            }
            csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeCategoryToFile(ArrayList<Category> categoryArr) {

        String path = getCategoryPath();
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(getCategoryData(), true);
            csvWriter.append("id" + "," + "categoryName");
            csvWriter.flush();
            for (Category category : categoryArr) {
                csvWriter.append("\n");
                csvWriter.append(category.getId() + "," + category.getCategoryName());
                csvWriter.flush();

            }
            csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void AddCategory() {
        String categoryName;
        categoryName = enterString(StringUtil.ENTER_CATEGORY_NAME);

        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(getCategoryData(), true);
            int id = (int) (Math.random() * 100);
            csvWriter.append("\n");
            csvWriter.append(id + "," + categoryName);
            csvWriter.flush();
            csvWriter.close();
            println(StringUtil.CATEGORY_SUCCESSFUL);
            adminController.adminMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void invalidException(AppException e) {
        println(e.getMessage());
        adminCategoryPage.categoryChoices();
    }
}
