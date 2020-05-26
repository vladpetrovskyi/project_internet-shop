# Internet shop
![Header Image](src/main/resources/main_page.png)
# Table of Contents
[Project purpose](#purpose)<br>
[Available functions](#avaiable_functions)<br>
[Project structure](#structure)<br>
[For developer](#developer-start)<br>
[Authors](#authors)
# <a name="purpose"></a>Project purpose

This is a template for creating a fully working e-store with a basic interface.
It implements typical for an online store functions and has the login and registration forms.


# <a name="avvailable_functions"></a>Available Functions
For **all** users: 
* view menu of the store;
* view items of the store;
* registration;
* log in;
* log out.
  
For users with a **USER role only**: 
* add products to user's shopping cart;
* delete products from user's shopping cart;
* view all user's orders;
* complete order;
* view a list of selected items in user`s shopping cart.

For users with an **ADMIN role only**:
* add items to a catalog;
* delete items from a catalog;
* view a list of all users;
* delete users from the store;


# <a name="structure"></a>Project Structure
* Java 11
* Maven 4.0.0
* javax.servlet-api 3.1.0
* jstl 1.2
* log4j 1.2.17
* maven-checkstyle-plugin
* mysql-connector-java 8.0.18


# <a name="developer-start"></a>For developer
Open the project in your IDE.

Add it as maven project.

Configure Tomcat:
* add artifact
* add sdk 11.0.3

Add sdk 11.0.3 in project structure.

Use file /internet-shop_project/src/main/resources/init_db.sql to create schema and all the tables required by this app in MySQL database.

At /internet-shop_project/src/main/java/mate/academy/internetshop/factory/DaoAndServiceFactory class use username and password for your DB to create a Connection.

Change a path in /jv-internet-shop/src/main/resources/log4j.properties. It has to reach your logFile.

Run the project.

If you first time launch this project: 
 * Run InjectDefaultUsersController by URL = .../internet_shop_war_exploded/injectDefaultUsers to create default users.

By default, there are one user with the USER role (login = "User1", password = "1") 
and one with an ADMIN role (login = "Admin1", password = "1"). 


# <a name="authors"></a>Authors
[Vlad Petrovskyi](https://github.com/vladpetrovskyi)