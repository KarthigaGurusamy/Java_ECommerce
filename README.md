Java_ECommerce
--------------

This java console application for E-Commerce has the following sections:
------------------------------------------------------------------------
    1. Login - Existing User and Admin
    2. Register - New User
    3. Home - User
        * View Categories 
            - View products based on category.
            - Add products to cart.
        * View Products 
            - Add products to cart.
        * View Cart 
            - Check out products from cart.
        * View Order 
        * Logout
    4. Home - Admin 
        * View Categories 
            - Add Category 
            - Remove Category 
        * View Products 
            - Add Product 
            - Edit Product 
            - Delete Product 
        * View Orders 
        * Logout

-> Java for coding 
-> 'CSV' file for storing and retrieving data.

Files used for each sections:
-----------------------------
    1. "User credential" file for storing user data.
    2. "Category data" file for storing categories.
    3. "Product data" file for storing Products.
    4. "Cart data" file for storing user cart details.
    5. "Order data" file for storing user order details.


Steps used and challenges faced to build this application:-
-----------------------------------------------------------

    Steps:
    ------
    1. Used MVC (Model, View, Controller) architecture for developing the console application.
    2. Added the required Models, Views and Controller along with Interface implementation to achieve abstraction.
    3. Added user-defined exceptions to catch anf handle the exceptions.
    4. Used encapsulation to hide data and used getter and setter for getting and setting the data for the models.
    5. Used "ArrayList" to store and manipulate data according to the user preferrences.
    6. Used "CSV" Files for handling data.
    7. Stored the file path, Scanner class other sensitive information in a separate Utility folder.
    8. Used Singleton pattern to avoid creating objects.
    9. Used "Date" class for handling date for orders.
    10. Handled exceptions for invalid choices.

    Challenges:
    -----------
    1. Faced "concurrentmodificationexception" - Caught this exception while working with Java collections.
        -> Solved it by not making any changes while the iterator is working on a ArrayList.
    2. Faced "StackOverFlow" - Caught this while creating parallel objects through constructor.
        -> Solved it by passing the instance "this" to other constructor.
    3. Had a problem while trying to update the cart count of a user product in the "CSV File".
        -> Solved it by storing the cart data by reading the "CSV File" and storing it in a ArrayList and updating the Arraylist and Writing it again to the "CSV File" 
           by deleting and creating the file again.


Working on the following sections 
---------------------------------
    1. Admin Home 
        * View Categories
            - Remove Category 
        * View Products 
            - Edit Product 
            - Delete Product 

Credentials 
-----------
  -> For Login use the following credentials
      *Email = "a" | Password = "a"
