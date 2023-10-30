package com.karthigaecommerce.controller;

import com.karthigaecommerce.controller.Implementation.IAppController;
import com.karthigaecommerce.utils.AppOutput;
import com.karthigaecommerce.views.HomePage;
import com.karthigaecommerce.views.WelcomePage;

import static com.karthigaecommerce.utils.AppOutput.print;

public class AppController implements IAppController {

    private final WelcomePage welcomePage;
    private final AuthController authController;

    public AppController() {
        this.welcomePage = new WelcomePage();
        this.authController= new AuthController();
    }

    @Override
    public void initiate() {
        welcomePage.welcomeMessage();
        authController.authMenu();
    }


}
