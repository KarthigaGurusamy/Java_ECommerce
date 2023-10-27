package com.karthigaecommerce.controller;

import com.karthigaecommerce.controller.Implementation.IHomeController;
import com.karthigaecommerce.views.HomePage;
import com.karthigaecommerce.views.WelcomePage;

public class HomeController implements IHomeController {

    private final HomePage homePage;

    public HomeController() {
        this.homePage = new HomePage();
    }

    @Override
    public void printMenu() {
        homePage.printMenu();
    }
}
