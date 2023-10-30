package com.karthigaecommerce.controller;

import com.karthigaecommerce.models.Category;
import com.karthigaecommerce.models.Order;
import com.karthigaecommerce.models.Product;
import com.karthigaecommerce.views.AdminOrderPage;
import com.karthigaecommerce.views.OrderPage;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static com.karthigaecommerce.controller.ProductController.getProductArr;
import static com.karthigaecommerce.utils.FileUtil.getOrdersData;
import static com.karthigaecommerce.utils.UserUtil.getLoggedInUser;
import static java.lang.Integer.parseInt;

public class AdminOrderController {

    private final AdminOrderPage adminOrderPage;
    private final AdminController adminController;
    public AdminOrderController(AdminController adminController) {
        this.adminOrderPage = new AdminOrderPage();
        this.adminController=adminController;
    }



    public void viewOrders() {
        adminOrderPage.viewOrders(AdminController.getOrderArr());
        adminController.adminMenu();
    }
}
