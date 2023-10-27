package com.karthigaecommerce.views;

import com.karthigaecommerce.utils.StringUtil;

import static com.karthigaecommerce.utils.AppOutput.print;
import static com.karthigaecommerce.utils.AppOutput.println;

public class WelcomePage {
    public void welcomeMessage() {
        println(StringUtil.WELCOME_MESSAGE);

    }

    public void printAuthMenu() {
        println(StringUtil.MENU);
    }
}
