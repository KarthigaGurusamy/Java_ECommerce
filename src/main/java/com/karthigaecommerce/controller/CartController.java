package com.karthigaecommerce.controller;
import com.karthigaecommerce.models.*;
import com.karthigaecommerce.utils.AppException;
import com.karthigaecommerce.utils.StringUtil;
import com.karthigaecommerce.views.CartPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static com.karthigaecommerce.controller.HomeController.getCategoryArr;
import static com.karthigaecommerce.controller.OrderController.orderedItems;
import static com.karthigaecommerce.controller.ProductController.getProductArr;
import static com.karthigaecommerce.utils.AppInput.enterInteger;
import static com.karthigaecommerce.utils.AppOutput.println;
import static com.karthigaecommerce.utils.FileUtil.*;
import static com.karthigaecommerce.utils.UserUtil.getLoggedInUser;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class CartController {


    private final CartPage cartPage;
    private final HomeController homeController;


    public CartController(HomeController homeController) {
        this.cartPage = new CartPage();
        this.homeController = homeController;
    }

    private static ArrayList<Cart> cart = new ArrayList<Cart>();

    public static ArrayList<Cart> getCartArr() {
        return cart;
    }

    private ArrayList<Cart> getCartDataFile() {
        ArrayList<Cart> cart = new ArrayList<Cart>();

        try {
            Scanner scanner = new Scanner(getCartData());
            while (scanner.hasNext()) {
                String[] value = scanner.nextLine().split(",");

                if (!value[0].equals("id") && !value[0].equals("")) {


                    String catName = "";
                    for (Category category : getCategoryArr()) {
                        if (parseInt(value[3]) == category.getId()) {
                            catName = category.getCategoryName();
                            break;
                        }
                    }

                    Category categoryObj = new Category(parseInt(value[3]), catName);
                    Product productObj = new Product(parseInt(value[2]), categoryObj, value[4], value[5], parseDouble(value[6]), parseInt(value[7]));
                    Cart cartObj = new Cart(parseInt(value[0]), getLoggedInUser(), productObj, parseInt(value[8]));
                    cart.add(cartObj);

                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cart;

    }

    public void AddtoCart() {

        cart = getCartDataFile();
        int choice;

        try {
            choice = enterInteger(StringUtil.PRODUCT_CHOICE);
            boolean isProduct = false;
            for (Product product : getProductArr()) {
                if (product.getId() == choice) {
                    isProduct = true;
                    break;
                }
            }
            if (isProduct) {

                setProductToCart(choice);
            } else {
                invalidException(new AppException(StringUtil.INVALID_PRODUCT));
            }
        } catch (AppException e) {
            invalidException(e);
        }

    }


    private void setProductToCart(int productId) {

        boolean isProductInCart = false;
        User user = getLoggedInUser();
        for (Cart cartItem : cart) {
            if (cartItem.getProduct().getId() == productId && cartItem.getUser().getId()==user.getId()) {
                isProductInCart = true;
            }
        }
        if (isProductInCart) {
            UpdateCartProduct(productId);
        } else {
            AddCartProduct(productId);
        }

        WriteToFile();
        println(StringUtil.CART_SUCCESSFUL);
        homeController.printMenu();


    }

    private void AddCartProduct(int productId) {
        Product product = null;
        for (Product data : getProductArr()) {
            if (data.getId() == productId) {
                product = data;
                break;
            }
        }
        int rand = (int) (Math.random() * 100);
        Cart cartObj = new Cart(rand, getLoggedInUser(), product, 1);
        cart.add(cartObj);
    }

    private void UpdateCartProduct(int productId) {
        User user = getLoggedInUser();
        for (Cart cartItem : cart) {
            if (cartItem.getProduct().getId() == productId && cartItem.getUser().getId() == user.getId() && cartItem.getUser().getEmail().equals(user.getEmail())) {
                cartItem.setCount(cartItem.getCount() + 1);
                Product product = cartItem.getProduct();
                product.setPrice(product.getPrice()+product.getPrice());
                cartItem.setProduct(product);
            }
        }

    }


    private void writeToCartFile() {

        String path = getCartPath();
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(getCartData(), true);

            csvWriter.append("id" + "," + "userId" + "," + "productId" + "," + "categoryId" + "," + "title"
                    + "," + "description" + "," + "price" + "," + "stocks" + "," + "count");
            csvWriter.flush();
            for (Cart cartObj : cart) {
                csvWriter.append("\n");
                csvWriter.append(cartObj.getId() + "," + cartObj.getUser().getId() + "," + cartObj.getProduct().getId() + "," + cartObj.getProduct().getCategory().getId() + "," + cartObj.getProduct().getTitle()
                        + "," + cartObj.getProduct().getDescription() + "," + cartObj.getProduct().getPrice() + "," + cartObj.getProduct().getStocks() + "," + cartObj.getCount());
                csvWriter.flush();


            }
            csvWriter.close();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void invalidException(AppException e) {
        println(e.getMessage());
        homeController.printMenu();
    }

    public void WriteToFile() {
        writeToCartFile();
    }

    public void printCartItems() {
        cart = getCartDataFile();
        User user = getLoggedInUser();
        boolean isUserCart=false;
        for(Cart cartItem:cart)
        {
            if(cartItem.getUser().getId()==user.getId())
            {
                isUserCart=true;break;
            }
        }
        if(isUserCart)
        {
            cartPage.printCartItems(cart);
            println(StringUtil.STATIC_CHOICES);
            println(StringUtil.CHECK_OUT);

            int choice;
            try {
                choice = enterInteger(StringUtil.CHOICE);
                if (choice == 1) {
                    orderedItems();
                    println(StringUtil.ORDER_SUCCESSFUL);
                    cart.removeIf((item) -> item.getUser().getId() == getLoggedInUser().getId());
                    WriteToFile();
                    homeController.printMenu();
                } else {
                    invalidCheckoutException(new AppException(StringUtil.INVALID_CHOICE));
                }
            } catch (AppException e) {
                invalidCheckoutException(e);
            }
        }
        else
        {
            println(StringUtil.CART_EMPTY);
            homeController.printMenu();
        }



    }

    private void invalidCheckoutException(AppException e) {
        println(e.getMessage());
        printCartItems();
    }


}
