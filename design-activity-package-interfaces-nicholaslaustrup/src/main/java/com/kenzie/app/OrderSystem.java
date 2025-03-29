package com.kenzie.app;

// Define your objects here

import java.lang.reflect.Array;

//Customer Interface
interface Customer {
    void createProfile(String userID);
    void updateProfile(String userID);
    void login(String userID, String password);
        }

//Payment Interface
interface Payment {
    void confirmPayment(double total);
    void printReceipt();
}


//Abstract Classes:
//* OrderSystem
abstract class OrderSystem {
    //Properties
    public Menu[] menuList;
    public String paymentOption;
    public Order currentOrder;
    public Customer currentCustomer;

    //Methods
    public abstract void selectMenu(String menu);
    public abstract void makePayment(Order order);
}


//* Order
abstract class Order {
    //Properties
    public MenuItem[] items;
    public double total;
    public String status;

    //Methods
    public abstract void addItem(int itemID);
    public abstract void removeItem(int itemID);
    public abstract double getTotal();
}


//* Menu
abstract class Menu {
    //Properties
    public String name;
    public MenuItem[] items;

    //Methods
    public abstract void displayMenu();
    public abstract void addItem(int itemID);
    public abstract void removeItem(int itemID);
}


//* MenuItem
abstract class MenuItem {
    //Properties
    public int itemID;
    public String name;
    public double price;
    public String availability;

    //Methods
    public abstract double getPrice();
    public abstract void setPrice(double price);
    public abstract void removeItem(int itemID);
}
