package com.kenzie.app;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("This will only compile if the required interfaces and abstract classes are defined correctly");
    }
}

//implement Payment
class CardPayment implements Payment {
    public void confirmPayment(double payment) {
        System.out.println("Payment confirmed");
    }
    public void printReceipt(){
        System.out.println("Receipt displayed");
    }
}

//implement Customer
class OnlineCustomer implements Customer {
    public void createProfile(String userID){
        System.out.println("Profile created");
    }
    public void updateProfile(String userID){
        System.out.println("Profile updated");
    }
    public void login(String userID,String password){
        System.out.println("Login");
    }
}

class OnlineOrderSystem extends OrderSystem {
    public Menu[] menuList[];
    public String paymentOption;
    public Order currentOrder;
    public Customer currentCustomer;

    public void selectMenu(String menu) {
        System.out.println("Select Menu");
    }
    public void makePayment(Order order){
        System.out.println("Make Payment");
    }
}

class FoodOrder extends Order {
    public MenuItem[] items;
    public double total;
    public String status;

    public void addItem(int itemID){
        System.out.println("add item");
    }
    public void removeItem(int itemID){
        System.out.println("remove item");
    }
    public double getTotal(){
        System.out.println("Get total");
        return this.total;
    }
}

class RestaurantMenu extends Menu {
    public String name;
    public ArrayList<MenuItem> items;
    
    public void displayMenu(){
        System.out.println("Display menu");
    }

    public void addItem(int itemID){
        System.out.println("Add item");
    }
    public void removeItem(int itemID){
        System.out.println("Remove item");
    }
}

class FoodItem extends MenuItem {
    private int itemID;
    private String item;
    private double price;
    private boolean isAvailable;

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }
    public void removeItem(int itemID){
        System.out.println("Remove price");
    }
}
