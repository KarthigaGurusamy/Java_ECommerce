package com.karthigaecommerce.views;

import com.karthigaecommerce.models.Category;
import com.karthigaecommerce.utils.StringUtil;

import java.util.ArrayList;

import static com.karthigaecommerce.utils.AppOutput.println;

public class AdminPage {
    public void adminMenu() {
        println(StringUtil.ADMIN_WELCOME);
        println(StringUtil.ADMIN_CHOICE);
    }


}
