package com.karthigaecommerce.controller;

import com.karthigaecommerce.models.Category;
import com.karthigaecommerce.models.Order;
import com.karthigaecommerce.models.Product;
import com.karthigaecommerce.utils.AppException;
import com.karthigaecommerce.utils.StringUtil;
import com.karthigaecommerce.views.AdminPage;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static com.karthigaecommerce.utils.AppInput.enterInteger;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.FileUtil.*;
import static com.karthigaecommerce.utils.UserUtil.getLoggedInUser;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AdminController {


    private final AdminPage adminPage;
    private final AdminCategoryController adminCategoryController;
    private final AdminProductController adminProductController;
    private final AdminOrderController adminOrderController;
    public AdminController() {
        this.adminPage = new AdminPage();
        this.adminCategoryController= new AdminCategoryController(this);
        this.adminOrderController = new AdminOrderController(this);
        this.adminProductController = new AdminProductController(this);
    }

    private static ArrayList<Category> categoryData = new ArrayList<Category>();
    private static ArrayList<Product> productData = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Category> getCategoryDataFile(){
        ArrayList<Category> categoryData = new ArrayList<Category>();
        try {
            Scanner scanner = new Scanner(getCategoryData());
            while (scanner.hasNext()) {
                String[] value = scanner.next().split(",");
                if (!value[0].equals("id")) {

                    Category category = new Category(parseInt(value[0]), value[1]);
                    categoryData.add(category);

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return categoryData;
    }

    private ArrayList<Product> getProductDataFile() {
        ArrayList<Product> productData = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(getProductData());
            while (scanner.hasNext()) {
                String[] value = scanner.nextLine().split(",");

                if (!value[0].equals("id") && !value[0].equals("")) {

                    ArrayList<Category> categoryData = AdminController.getCategoryArr();
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
    private ArrayList<Order> getOrdersDataFile() {
        try {
            Scanner scanner = new Scanner(getOrdersData());
            while (scanner.hasNext()) {
                String[] value = scanner.nextLine().split(",");

                if (!value[0].equals("id") && !value[0].equals("")) {


                    int productId=0;
                    String title="";
                    String description="";
                    double price=0;
                    int stocks=0;
                    int categoryId =0;
                    String categoryName="";
                    for (Product data : getProductArr()) {
                        if (parseInt(value[3]) == data.getId()) {
                            productId=data.getId();
                            title= data.getTitle();
                            description=data.getDescription();
                            price=data.getPrice();
                            stocks=data.getStocks();
                            categoryId=data.getCategory().getId();
                            categoryName=data.getCategory().getCategoryName();
                            break;
                        }
                    }
                    Category category = new Category(categoryId,categoryName);
                    Product product = new Product(productId,category,title,description,price,stocks);

                    String d = value[2];
                    String pattern ="dd/MM/yyyy";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

                    try {
                        Date date = dateFormat.parse(d);
                        Order orderObj = new Order(parseInt(value[0]),getLoggedInUser(),date ,product);
                        orders.add(orderObj);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
    public static ArrayList<Category> getCategoryArr()
    {
        return  categoryData;
    }

    public static ArrayList<Product> getProductArr()
    {
        return productData;
    }
    public static ArrayList<Order> getOrderArr()
    {
        return orders;
    }

    public void adminMenu() {
        adminPage.adminMenu();
        categoryData = getCategoryDataFile();
        productData = getProductDataFile();
        orders = getOrdersDataFile();

        int choice;
        try {
            choice = enterInteger(StringUtil.CHOICE);
            if (choice == 1) {
                adminCategoryController.viewCategories(categoryData);


            } else if (choice == 2) {
                adminProductController.viewProducts();

            }
            else if(choice==3)
            {
                adminOrderController.viewOrders();
            }
            else if(choice==4)
            {
                println(StringUtil.THANK_YOU_MESSAGE);
            }
            else {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException e) {
            invalidException(e);

        }
    }

    private void invalidException(AppException e) {
        println(e.getMessage());
        adminMenu();
    }

}
