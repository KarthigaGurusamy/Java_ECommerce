package com.karthigaecommerce.views;

import com.karthigaecommerce.models.Category;
import com.karthigaecommerce.utils.StringUtil;

import java.util.ArrayList;

import static com.karthigaecommerce.utils.AppOutput.println;

public class HomePage {


    public void printMenu(ArrayList<Category> categoryData) {
        println(StringUtil.CATEGORY_MESSAGE);
        for(Category data: categoryData)
        {
            println(data.getId()+". " + data.getCategoryName());
        }
        println(StringUtil.BACK);
    }

    public void printChoice() {
        println(StringUtil.HOMEPAGE);
    }


}
