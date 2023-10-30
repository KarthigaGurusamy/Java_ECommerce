package com.karthigaecommerce.controller;

import com.karthigaecommerce.controller.Implementation.IAuthController;
import com.karthigaecommerce.models.*;
import com.karthigaecommerce.utils.AppException;
import com.karthigaecommerce.utils.StringUtil;
import com.karthigaecommerce.views.AuthPage;
import com.karthigaecommerce.views.LoginPage;
import com.karthigaecommerce.views.RegisterPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.karthigaecommerce.utils.AppInput.enterInteger;
import static com.karthigaecommerce.utils.AppInput.enterString;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.FileUtil.getCredentialsFile;
import static com.karthigaecommerce.utils.UserUtil.setLoggedInUser;
import static java.lang.Integer.parseInt;


public class AuthController implements IAuthController {


    private final HomeController homeController;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;

    private final AuthPage authPage;
    private final AdminController adminController;

    public AuthController() {

        this.homeController = new HomeController();
        this.loginPage = new LoginPage();
        this.registerPage = new RegisterPage();
        this.authPage = new AuthPage();
        this.adminController = new AdminController();
    }


    private static ArrayList<User> userData = new ArrayList<>();

    public static ArrayList<User> getUserArray() {
        return userData;
    }

    ArrayList<User> getUserDataFromFile() {
        ArrayList<User> userData = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(getCredentialsFile());
            while (scanner.hasNext()) {
                String[] value = scanner.next().split(",");
                if (!value[0].equals("id")) {

                    User user = new User(parseInt(value[0]), value[1], value[2], value[3], Role.valueOf(value[4]));
                    userData.add(user);

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return userData;
    }

    @Override
    public void authMenu() {
        userData = getUserDataFromFile();
        authPage.printAuthMenu();
        int choice;
        try {
            choice = enterInteger(StringUtil.CHOICE);
            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else {
                invalidException(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException e) {
            invalidException(e);

        }

    }

    private void invalidException(AppException e) {
        println(e.getMessage());
        authMenu();
    }


    @Override
    public void login() {

        String email = enterString(StringUtil.ENTER_EMAIL);
        String password = enterString(StringUtil.ENTER_PASSWORD);

        boolean isUser = ValidateUser(email, password);

        if (isUser) {
            if(email.equals("karthiga@admin.com"))
            {
                adminController.adminMenu();
            }
            else
            {
                homeController.printMenu();

            }
        } else {
            loginPage.printInvalidCredentials();
            authMenu();
        }

    }

    public boolean ValidateUser(String email, String password) {
        ArrayList<User> userData = getUserArray();
        for (User user : userData) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                setLoggedInUser(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public void register() {
        String name, email, password, confirm_password;
        name = enterString(StringUtil.ENTER_NAME);
        email = enterString(StringUtil.ENTER_EMAIL);
        password = enterString(StringUtil.ENTER_PASSWORD);
        confirm_password = enterString(StringUtil.ENTER_CONFIRM_PASSWORD);

        if (password.equals(confirm_password)) {
            FileWriter csvWriter = null;
            try {
                csvWriter = new FileWriter(getCredentialsFile(), true);
                int id = (int) (Math.random() * 100);
                String role = "";
                if (email.contains("admin")) {
                    role = Role.ADMIN.toString();
                } else {
                    role = Role.USER.toString();
                }
                csvWriter.append("\n");
                csvWriter.append(id + "," + email + "," + password + "," + name + "," + role);
                csvWriter.flush();
                csvWriter.close();
                registerPage.printRegistrationSuccessful();

                authMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            registerPage.passwordMisMatch();
            authMenu();
        }

    }



}
