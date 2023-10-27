package com.karthigaecommerce.views;

import com.karthigaecommerce.utils.StringUtil;

import static com.karthigaecommerce.utils.AppOutput.println;

public class LoginPage {
    public void printInvalidCredentials() {
        println(StringUtil.INVALID_CREDENTIALS);
    }
}
