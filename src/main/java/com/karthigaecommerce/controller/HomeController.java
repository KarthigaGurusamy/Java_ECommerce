package com.karthigaecommerce.controller;

import com.karthigaecommerce.controller.Implementation.IHomeController;
import com.karthigaecommerce.models.Category;
import com.karthigaecommerce.models.Order;
import com.karthigaecommerce.models.Role;
import com.karthigaecommerce.models.User;
import com.karthigaecommerce.utils.AppException;
import com.karthigaecommerce.utils.StringUtil;
import com.karthigaecommerce.views.HomePage;
import com.karthigaecommerce.views.WelcomePage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.karthigaecommerce.utils.AppInput.enterInteger;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.FileUtil.getCategoryData;
import static com.karthigaecommerce.utils.FileUtil.getCredentialsFile;
import static com.karthigaecommerce.utils.UserUtil.removeLoggedInUser;
import static java.lang.Integer.parseInt;

public class HomeController implements IHomeController {

    private final HomePage homePage;
    private final ProductController productController;

    private final CartController cartController;

    private final OrderController orderController;


    public HomeController() {
        this.homePage = new HomePage();
        this.cartController = new CartController(this);
        this.orderController = new OrderController(this);
        this.productController = new ProductController(this, cartController);

    }

    private static ArrayList<Category> categoryData = new ArrayList<Category>();

    ArrayList<Category> getCategoryDataFile() {
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

    public static ArrayList<Category> getCategoryArr() {
        return categoryData;
    }

    @Override
    public void printMenu() {
        categoryData = getCategoryDataFile();
        println(StringUtil.USER_WELCOME);
        homePage.printChoice();

        int choice;
        try {
            choice = enterInteger(StringUtil.CHOICE);
            if (choice == 1) {
                productController.printMenu();
            } else if (choice == 2) {
                productController.printProducts();
            } else if (choice == 3) {
                cartController.printCartItems();
            } else if (choice == 4) {
                orderController.OrderedItemsList();
            } else if (choice == 5) {

                removeLoggedInUser();
                println(StringUtil.THANK_YOU_MESSAGE);
            } else {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }

        } catch (AppException e) {
            invalidException(e);

        }

    }


    private void invalidException(AppException e) {
        println(e.getMessage());
        printMenu();
    }
}
