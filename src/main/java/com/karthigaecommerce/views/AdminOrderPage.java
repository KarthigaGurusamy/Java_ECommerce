package com.karthigaecommerce.views;

import com.karthigaecommerce.models.Order;
import com.karthigaecommerce.utils.StringUtil;

import java.util.ArrayList;

import static com.karthigaecommerce.controller.OrderController.getOrderArr;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.UserUtil.getLoggedInUser;

public class AdminOrderPage {
    public void viewOrders(ArrayList<Order> orders) {
        println(StringUtil.ORDER_MESSAGE);

        for(Order order:orders)
        {
            println(order.getId()+". " + order.getProduct().getTitle()+", "+order.getUser().getEmail() +", "+ order.getDate() +", ₹."+order.getProduct().getPrice());

        }
    }
}
