package com.karthigaecommerce.views;

import com.karthigaecommerce.models.Order;
import com.karthigaecommerce.models.User;
import com.karthigaecommerce.utils.StringUtil;

import java.util.ArrayList;

import static com.karthigaecommerce.controller.OrderController.getOrderArr;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.UserUtil.getLoggedInUser;


public class OrderPage {
    public void OrderdItemsList(ArrayList<Order> orders) {
        println(StringUtil.ORDER_MESSAGE);

        User user = getLoggedInUser();
        for(Order order:orders)
        {
            if(order.getUser().getId()==user.getId())
            {
                println(order.getId()+". " + order.getProduct().getTitle() +", "+ order.getDate() +", ₹."+order.getProduct().getPrice());
            }
        }
    }


}
