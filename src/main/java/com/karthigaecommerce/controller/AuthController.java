package com.karthigaecommerce.controller;

import com.karthigaecommerce.App;
import com.karthigaecommerce.controller.Implementation.IAuthController;
import com.karthigaecommerce.models.Role;
import com.karthigaecommerce.models.User;
import com.karthigaecommerce.utils.AppException;
import com.karthigaecommerce.utils.AppInput;
import com.karthigaecommerce.utils.StringUtil;
import com.karthigaecommerce.views.LoginPage;
import com.karthigaecommerce.views.RegisterPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.karthigaecommerce.utils.AppInput.enterInteger;
import static com.karthigaecommerce.utils.AppInput.enterString;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.FileUtil.getCredentialsFile;
import static com.karthigaecommerce.utils.FileUtil.getCredentialsFolder;

public class AuthController implements IAuthController {

    private final AppController appController;
    private final HomeController homeController;
    private  final LoginPage loginPage;
    private final RegisterPage registerPage;

    public AuthController(AppController appController) {
        this.appController = appController;
        this.homeController = new HomeController();
        this.loginPage = new LoginPage();
        this.registerPage = new RegisterPage();
    }


    void setStaticUsers()  {
        try {
            boolean isFolder = getCredentialsFolder("src/main/java/com/karthigaecommerce/asserts");
        } catch (Exception e) {

        }
    }



    @Override
    public void authMenu() {
        setStaticUsers();
        appController.printAuthMenu();
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

        boolean isUser = ValidateUser(email,password);

        if(isUser)
        {
            homeController.printMenu();
        }
        else
        {
            loginPage.printInvalidCredentials();
            authMenu();
        }

    }

    public boolean ValidateUser(String email,String password) {
        try {
            Scanner scanner = new Scanner(getCredentialsFile());
            while (scanner.hasNext()) {
                String[] value = scanner.next().split(",");
                if(!value[0].equals("id"))
                {
                    if (value[2].equals(email) && value[3].equals(password)) {
                        if(value[0].equals("1") || value[0].equals("2"))
                        {
                            User user = new User();
                            user.setId(Integer.parseInt(value[0]));
                            user.setName(value[1]);
                            user.setEmail(value[2]);
                            user.setPassword(value[3]);
                            String[] emailSplit = value[2].split("@");
                            if (emailSplit[1].equals("admin.com"))
                            {
                                user.setRole(Role.ADMIN);

                            }
                            else
                            {
                                user.setRole(Role.USER);
                            }

                        }

                        return true;
                    }
                }
            }
            scanner.close();


        } catch (Exception ex) {
            println(ex.getMessage());
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
            try {
                FileWriter csvWriter = new FileWriter(getCredentialsFile(), true);
                int id = (int) (Math.random() * 100);
                csvWriter.append("\n");
                csvWriter.append(id + "," + name + "," + email + "," + password);
                csvWriter.flush();
                csvWriter.close();

                registerPage.printRegistrationSuccessful();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            registerPage.passwordMisMatch();
        }
        authMenu();

    }

    @Override
    public void logout() {

    }
}
