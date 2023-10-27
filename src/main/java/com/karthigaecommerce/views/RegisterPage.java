package com.karthigaecommerce.views;

import com.karthigaecommerce.utils.StringUtil;
import static com.karthigaecommerce.utils.AppOutput.println;

public class RegisterPage {
    public void passwordMisMatch() {
        println(StringUtil.PASSWORD_MISMATCH);
    }

    public void printRegistrationSuccessful() {
        println(StringUtil.REGISTRATION_SUCCESSFUL);
    }
}
