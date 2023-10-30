package com.karthigaecommerce.utils;

public class StringUtil {



    static String RESET = "\u001B[0m";

    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String BLUE = "\u001B[34m";
    static String BOLD = "\u001B[1m";
    static String UNDERLINE = "\u001B[4m";
    static String YELLOW = "\u001B[33m";
    public static final String CART_EMPTY = BLUE+BOLD+"OOPS! Your Cart is Empty!"+RESET;
    public static final String ORDER_EMPTY = BLUE+BOLD+"OOPS! Nothing is in your order!"+RESET;
    public static final String WELCOME_MESSAGE = BLUE + BOLD +UNDERLINE+ "Welcome to Java Shopping!!!" +RESET;
    public static final String MENU = "1.Login \n2.Register";
    public static final String CHOICE = YELLOW+"Enter a choice : " + RESET;

    public static final String INVALID_CHOICE = RED+"Invalid choice! Please enter a valid choice"+RESET;
    public static final String ENTER_EMAIL = "Enter your email: ";
    public static final String ENTER_PASSWORD = "Enter your password: ";
    public static final String INVALID_CREDENTIALS = RED+"Invalid credentials! Please Enter valid credentials"+RESET;
    public static final String ENTER_NAME = "Enter your name: ";
    public static final String ENTER_CONFIRM_PASSWORD = "Enter confirm password: ";
    public static final String PASSWORD_MISMATCH = RED+"Mismatch of password and confirm password!"+RESET;
    public static final String REGISTRATION_SUCCESSFUL = GREEN+"Registration Successful!!"+RESET;
    public static final String CATEGORY_MESSAGE =BLUE+BOLD+ "---Categories---"+RESET;
    public static final String PRODUCT_MESSAGE = BLUE+BOLD+"---Products---"+RESET;
    public static final String ENTER_PRODUCT_CHOICE = "1. Add to cart";
    public static final String PRODUCT_CHOICE = "Enter the product Id to add the product to cart:";
    public static final String HOMEPAGE = "1. Categories\n2. Products\n3. Cart\n4. Orders\n5. Logout";
    public static final String STATIC_CHOICES = YELLOW+"---Options---"+RESET;
    public static final String BACK = "100. Back";
    public static final String THANK_YOU_MESSAGE = BLUE+BOLD + "---Thank you!! Visit Again!!---"+RESET;
    public static final String INVALID_PRODUCT = RED+"Invalid product id please enter a valid product id..."+RESET;
    public static final String CART_SUCCESSFUL = GREEN+"Product added to cart successfully!"+RESET;
    public static final String CHECK_OUT = "1. Check out";
    public static final String CART_MESSAGE = BLUE+BOLD+"---Cart Products---"+RESET;
    public static final String ORDER_SUCCESSFUL = GREEN+"Orders placed successfully! Thank you for shopping with us!"+RESET;
    public static final String ORDER_MESSAGE = BLUE+BOLD+"---Orders---"+RESET;
    public static final String ADMIN_WELCOME = BLUE + BOLD +UNDERLINE +"---Welcome Admin---"+RESET;
    public static final String ADMIN_CHOICE = "1. View Categories\n2. View Products\n3. View Orders\n4. Logout";
    public static final String CATEGORY_CHOICES = "1. Add Category\n2. Remove Category\n3. Back";
    public static final String ENTER_CATEGORY_NAME = "Enter category Name: ";
    public static final String CATEGORY_SUCCESSFUL = GREEN+"---Category added successfully!---"+RESET;
    public static final String ADMIN_PRODUCT_CHOICE = "1. Add Product\n2. Edit Product\n3. Remove Product\n4. Back";
    public static final String ENTER_PRODUCT_NAME = "Enter product name: ";
    public static final String ENTER_PRODUCT_DESCRIPTION = "Enter product description: ";
    public static final String ENTER_PRODUCT_PRICE = "Enter product price: ";
    public static final String ENTER_PRODUCT_STOCKS = "Enter product stocks: ";
    public static final String PRODUCT_SUCCESSFUL = GREEN+"---Product Added Successfully!---"+RESET;
    public static final String USER_WELCOME = BLUE + BOLD +UNDERLINE +"---Welcome User---"+RESET;
    public static final String CATEGORY_DELETED_MESSAGE = GREEN + "---Category removed succesfully!---" +RESET;
}
