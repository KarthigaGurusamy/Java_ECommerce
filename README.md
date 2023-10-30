Java_ECommerce
--------------

This java console application for E-Commerce has the following sections:
------------------------------------------------------------------------
    👤 Login - Existing User and Admin
    👥 Register - New User
    🏠 Home - User
        👀 View Categories 
            🛍️ View products based on category.
            ⏬ Add products to cart.
        👀 View Products 
            ⏬ Add products to cart.
        🛒 View Cart 
            ✔️ Check out products from cart.
        ➡️ View Order 
            📦 Check for placed orders.
        📤 Logout
    🏠 Home - Admin 
        👀 View Categories 
            ➕ Add Category 
            ➖ Remove Category 
        👀 View Products 
            ➕ Add Product 
            🔄 Edit Product 
            ➖ Delete Product 
        👀 View Orders 
        📤 Logout

-> '💻-Java' for coding 
-> '📁-CSV' file for storing and retrieving data.

Files used for each sections:
-----------------------------
    1. "📁-User credential" file for storing user data.
    2. "📁-Category data" file for storing categories.
    3. "📁-Product data" file for storing Products.
    4. "📁-Cart data" file for storing user cart details.
    5. "📁-Order data" file for storing user order details.


Steps used and challenges faced to build this application:-
-----------------------------------------------------------

    Steps:
    ------
    👉 Used MVC (Model, View, Controller) architecture for developing the console application.
    👉 Added the required Models, Views and Controller along with Interface implementation to achieve abstraction.
    👉 Added user-defined exceptions to catch anf handle the exceptions.
    👉 Used encapsulation to hide data and used getter and setter for getting and setting the data for the models.
    👉 Used "ArrayList" to store and manipulate data according to the user preferrences.
    👉 Used "CSV" Files for handling data.
    👉 Stored the file path, Scanner class other sensitive information in a separate Utility folder.
    👉 Used Singleton pattern to avoid creating objects.
    👉 Used "Date" class for handling date for orders.
    👉 Handled exceptions for invalid choices.

    Challenges:
    -----------
    🔴 Faced "concurrentmodificationexception" - Caught this exception while working with Java collections.
        -> Solved it by not making any changes while the iterator is working on a ArrayList.
    🔴 Faced "StackOverFlow" - Caught this while creating parallel objects through constructor.
        -> Solved it by passing the instance "this" to other constructor.
    🔴 Had a problem while trying to update the cart count of a user product in the "CSV File".
        -> Solved it by storing the cart data by reading the "CSV File" and storing it in a ArrayList and updating the Arraylist and                 Writing it again to the "CSV File" by deleting and creating the file again.


Working on the following sections 
---------------------------------
    🏠 Admin Home 
        👀 View Categories
            ➖ Remove Category 
        👀 View Products 
            🔄 Edit Product 
            ➖ Delete Product 

Credentials 
-----------
  -> For Login use the following credentials
      *Email = "a" | Password = "a"


Screenshots
-----------
👤 Login:
---------

![image](https://github.com/KarthigaGurusamy/Java_ECommerce/assets/145537707/dbf6e3f6-9e18-481b-b61d-7959135956b2)

 👀 Categories:
 --------------

 ![image](https://github.com/KarthigaGurusamy/Java_ECommerce/assets/145537707/1fce68dc-2986-494d-98ae-80e565845b74)

 👀 Products:
 ------------

 ![image](https://github.com/KarthigaGurusamy/Java_ECommerce/assets/145537707/b7d527b9-b02c-4901-9e96-4d1286061d3b)


🛒 Cart:
---------

![image](https://github.com/KarthigaGurusamy/Java_ECommerce/assets/145537707/26099ba6-fb8b-4b28-835a-272b31868ef6)


📦 Orders:
----------

![image](https://github.com/KarthigaGurusamy/Java_ECommerce/assets/145537707/6c0ae626-8164-4d1d-8a57-deac675d0064)


🔴 Handling Exceptions:
-----------------------

![image](https://github.com/KarthigaGurusamy/Java_ECommerce/assets/145537707/986de6f0-407d-45cf-9fad-7f12a641cb63)



