package com.karthigaecommerce.views;

import com.karthigaecommerce.models.Cart;
import com.karthigaecommerce.utils.StringUtil;

import java.util.ArrayList;

import static com.karthigaecommerce.controller.CartController.getCartArr;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.UserUtil.getLoggedInUser;

public class CartPage {
    public void printCartItems(ArrayList<Cart> cartData) {
        println(StringUtil.CART_MESSAGE);
        for(Cart cart: cartData)
        {
            if(cart.getUser()==getLoggedInUser())
            {
                println(cart.getId()+"." +cart.getProduct().getTitle() +", ₹."+ cart.getProduct().getPrice() +", Quantity: "+cart.getCount());

            }
        }
    }
}
