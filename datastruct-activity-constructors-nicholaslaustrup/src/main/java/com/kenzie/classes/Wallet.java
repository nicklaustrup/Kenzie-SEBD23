package com.kenzie.classes;

public class Wallet {

    //class objects
    public int numCreditCards; // Number of credit cards
    public String owner; //owner name
    public double totalCash; //total amount of cash


    //Constructors
    public Wallet(){
        this.numCreditCards = 0;
        this.owner = "";
        this.totalCash = 0;
    }
    public Wallet(String owner){
        this.owner = owner;
    }

    public Wallet(int numCreditCards,
                  String owner,
                  double totalCash){
        this.numCreditCards = numCreditCards;
        this.owner = owner;
        this.totalCash = totalCash;
    }

    //Methods
    public int getNumCreditCards() {

        return numCreditCards;
    }
    public void setNumCreditCards(int numCreditCards) {

        this.numCreditCards = numCreditCards;
    }

    public double getTotalCash() {
        return totalCash;
    }
    public void setTotalCash(double totalCash) {

        this.totalCash = totalCash;
    }

    public String getOwner() {

        return owner;
    }
    public void setOwner(String owner) {

        this.owner = owner;
    }



}
