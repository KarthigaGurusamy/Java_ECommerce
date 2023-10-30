package com.karthigaecommerce.controller;

import com.karthigaecommerce.models.*;
import com.karthigaecommerce.utils.AppException;
import com.karthigaecommerce.utils.StringUtil;
import com.karthigaecommerce.views.OrderPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static com.karthigaecommerce.controller.CartController.getCartArr;
import static com.karthigaecommerce.controller.HomeController.getCategoryArr;
import static com.karthigaecommerce.controller.ProductController.getProductArr;
import static com.karthigaecommerce.utils.AppInput.enterInteger;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.FileUtil.getCartData;
import static com.karthigaecommerce.utils.FileUtil.getOrdersData;
import static com.karthigaecommerce.utils.UserUtil.getLoggedInUser;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class OrderController {

    private final OrderPage orderPage;

    private final HomeController homeController;

    public OrderController(HomeController homeController) {
        this.orderPage = new OrderPage();
        this.homeController = homeController;
    }

    private static ArrayList<Order> orders = new ArrayList<>();

    public static void orderedItems() {
        ArrayList<Cart> userCart = getCartArr();
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(getOrdersData(), true);
            User user = getLoggedInUser();
            for (Cart cartObj : userCart) {
                if (cartObj.getUser().getId() == user.getId()) {
                    int rand = (int) (Math.random() * 100);
                    Date date = new Date();
                    String pattern = "dd/MM/yyyy";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

                    csvWriter.append("\n");
                    csvWriter.append(rand + "," + cartObj.getUser().getId() + "," + dateFormat.format(date) + "," + cartObj.getProduct().getId() + "," + cartObj.getProduct().getTitle()
                            + "," + cartObj.getProduct().getPrice() + "," + cartObj.getCount());
                    csvWriter.flush();

                }


            }
            csvWriter.close();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Order> getOrderArr() {
        return orders;
    }

    public void OrderedItemsList() {
        orders = getOrders();
        User user = getLoggedInUser();
        boolean isUserOrder = false;
        for (Order order : orders) {
            if (order.getUser().getId() == user.getId()) {
                isUserOrder = true;
                break;
            }
        }
        if (isUserOrder) {
            orderPage.OrderdItemsList(orders);
            println(StringUtil.BACK);
            int choice = 0;
            try {
                choice = enterInteger(StringUtil.CHOICE);
                if (choice == 100) {
                    homeController.printMenu();
                } else {
                    invalidException(new AppException(StringUtil.INVALID_CHOICE));
                }
            } catch (AppException e) {
                invalidException(e);
            }
        } else {
            println(StringUtil.ORDER_EMPTY);
            homeController.printMenu();
        }


    }

    private void invalidException(AppException e) {
        println(e.getMessage());
        OrderedItemsList();
    }

    private ArrayList<Order> getOrders() {
        try {
            Scanner scanner = new Scanner(getOrdersData());
            while (scanner.hasNext()) {
                String[] value = scanner.nextLine().split(",");

                if (!value[0].equals("id") && !value[0].equals("")) {

                    if (parseInt(value[1]) == getLoggedInUser().getId()) {
                        int productId = 0;
                        String title = "";
                        String description = "";
                        double price = 0;
                        int stocks = 0;
                        int categoryId = 0;
                        String categoryName = "";
                        for (Product data : getProductArr()) {
                            if (parseInt(value[3]) == data.getId()) {
                                productId = data.getId();
                                title = data.getTitle();
                                description = data.getDescription();
                                price = data.getPrice();
                                stocks = data.getStocks();
                                categoryId = data.getCategory().getId();
                                categoryName = data.getCategory().getCategoryName();
                                break;
                            }
                        }
                        Category category = new Category(categoryId, categoryName);
                        Product product = new Product(productId, category, title, description, price, stocks);

                        String d = value[2];
                        String pattern = "dd/MM/yyyy";
                        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

                        try {
                            Date date = dateFormat.parse(d);
                            Order orderObj = new Order(parseInt(value[0]), getLoggedInUser(), date, product);
                            orders.add(orderObj);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                    }


                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
}
