package com.karthigaecommerce.controller;

import com.karthigaecommerce.models.Category;
import com.karthigaecommerce.models.Product;
import com.karthigaecommerce.utils.AppException;
import com.karthigaecommerce.utils.StringUtil;
import com.karthigaecommerce.views.HomePage;
import com.karthigaecommerce.views.ProductPage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.karthigaecommerce.controller.HomeController.getCategoryArr;
import static com.karthigaecommerce.utils.AppInput.enterInteger;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.FileUtil.getCategoryData;
import static com.karthigaecommerce.utils.FileUtil.getProductData;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ProductController {

    private final ProductPage productPage;


    private final HomePage homePage;
    private final HomeController homeController;
    private final CartController cartController;

    public ProductController(HomeController homeController, CartController cartController) {
        this.productPage = new ProductPage();
        this.homePage = new HomePage();
        this.homeController=homeController;
        this.cartController=cartController;

    }

    private static ArrayList<Product> productData = new ArrayList<>();

    public static ArrayList<Product> getProductArr() {
        return productData;
    }

    ArrayList<Product> getProductDataFile() {
        ArrayList<Product> productData = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(getProductData());
            while (scanner.hasNext()) {
                String[] value = scanner.nextLine().split(",");

                if (!value[0].equals("id")) {

                    ArrayList<Category> categoryData = getCategoryArr();
                    int categoryId = parseInt(value[1]);

                    for (Category data : categoryData) {
                        if (data.getId() == categoryId) {
                            Category category = new Category(data.getId(), data.getCategoryName());
                            productData.add(new Product(parseInt(value[0]), category, value[2], value[3], parseDouble(value[4]), parseInt(value[5])));
                        }
                    }

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productData;
    }

    public void printMenu() {
        productData = getProductDataFile();
        ArrayList<Category> categoryData = getCategoryArr();
        homePage.printMenu(categoryData);
        int choice=0;
        try {
            choice = enterInteger(StringUtil.CHOICE);
            if(choice!=100)
            {
                boolean isCategory = false;
                for(Category category : getCategoryArr())
                {
                    if(category.getId()==choice)
                    {
                        isCategory=true;break;
                    }
                }
                if(isCategory)
                {
                    productPage.printProductMenu(choice, productData);
                    productsChoice();
                }
                else
                {
                    productPage.printAllProducts(productData);
                    productsChoice();
                }

            }
            else if(choice==100)
            {
                homeController.printMenu();
            }
            else
            {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }



        } catch (AppException e) {
            invalidException(e);

        }



    }

    public void productsChoice()
    {
        println(StringUtil.STATIC_CHOICES);
        println(StringUtil.ENTER_PRODUCT_CHOICE);

        int productChoice;
        try {

            productChoice = enterInteger(StringUtil.CHOICE);
            if(productChoice==1)
            {
                cartController.AddtoCart();
            }

            else
            {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }

        } catch (AppException e) {
            invalidException(e);

        }

    }

    private void invalidException(AppException e) {
        println(e.getMessage());
        productsChoice();
    }

    public void printProducts() {
        productData = getProductDataFile();
        productPage.printAllProducts(productData);
        productsChoice();
    }


}
