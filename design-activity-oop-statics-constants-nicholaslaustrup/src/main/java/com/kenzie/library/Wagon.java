package com.kenzie.library;

import java.util.Arrays;

public class Wagon {

    //class properties
    private int capacity;
    private Traveler[] passengers;


    //constructor
    public Wagon(int capacity){
        this.capacity = capacity;
        passengers = new Traveler[capacity];
    }

    //methods
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public int getCapacity(){
       return this.capacity;
    }
    public void setPassengers(Traveler[] passengers){
        this.passengers = passengers;
    }
    public Traveler[] getPassengers(){
        return this.passengers;
    }
    public int getAvailableSeatCount(){
        int seatCounter = 0;
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i]==null) {
                seatCounter++;
            }
        }
        return seatCounter;
    }
    public void join(Traveler traveler){
     if (getAvailableSeatCount() > 0) {
          int index = this.passengers.length - getAvailableSeatCount();
          this.passengers[index] = traveler;
         }
    }
    public boolean shouldQuarantine(){
        for (Traveler passenger : passengers) {
            if (passenger != null && !passenger.getIsHealthy()){
                return true;
            }
        }
        return false;
    }

    public int totalFood(){
        int foodCount = 0;
        for (Traveler passenger : passengers) {
            if (passenger != null) {
                foodCount += passenger.getFood();
            }
        }
        return foodCount;
    }

    public void loadWagon(int numTravelers, int numHunters, int numDoctors) {
        for (int i = 0; i < numTravelers; i++) {
            Traveler traveler = new Traveler();
            this.join(traveler);
        }
        for (int i = 0; i < numHunters; i++) {
            Hunter hunter = new Hunter();
            this.join(hunter);
        }
        for (int i = 0; i < numDoctors; i++) {
            Doctor doctor = new Doctor();
            this.join(doctor);
        }

    }

}
