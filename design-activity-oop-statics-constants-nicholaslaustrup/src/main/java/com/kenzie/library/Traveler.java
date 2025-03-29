package com.kenzie.library;

import java.util.Random;

public class Traveler {
    protected String name;
    protected int food;
    protected boolean isHealthy;


    //Constructors
    public Traveler(){
        this.food = 1;
        this.isHealthy = true;
    }

    public Traveler(String name){
        this.name = name;
        this.food = 1;
        this.isHealthy = true;
    }


    //Settters / Getters
    public void setName(){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setFood(int food){
        this.food = food;
    }
    public int getFood(){
        return this.food;
    }
    public void setIsHealthy(boolean isHealthy){
        this.isHealthy = isHealthy;
    }
    public boolean getIsHealthy(){
        return this.isHealthy;
    }

    //methods
  public void hunt() {
      int index = new Random().nextInt(WILDLIFE.values().length);
      WILDLIFE chooseAnimal = WILDLIFE.values()[index];
      if (chooseAnimal.equals(WILDLIFE.BISON)){
          this.food += 3;
      }
      else if(chooseAnimal.equals(WILDLIFE.DEER)){
          this.food += 2;
      }
      else if(chooseAnimal.equals(WILDLIFE.RABBIT)){
          this.food += 1;
      }
      System.out.println("I'm not much of a hunter but I might be able to bag a  " + chooseAnimal + ".");
  }

    public void eat(){
        if (this.food == 0) {
            this.isHealthy = false;
        } else {this.food -= 1;}
    }
}



class Doctor extends Traveler {
    //Additional method

    public void heal(Traveler traveler){
        traveler.isHealthy = true;
    }
  public Doctor(){
      super.food = 1;
      super.isHealthy = true;
  }
    public Doctor(String name){
        super.name = name;
        super.food = 1;
        super.isHealthy = true;
    }
  
}

class Hunter extends Traveler {
    //Constructors

    public Hunter(){
        super.food = 2;
        super.isHealthy = true;
    }
    public Hunter(String name){
        super.name = name;
        super.food = 2;
        super.isHealthy = true;
    }


//Methods
@Override
public void hunt(){
    int index = new Random().nextInt(WILDLIFE.values().length);
    WILDLIFE chooseAnimal = WILDLIFE.values()[index];
    if (chooseAnimal.equals(WILDLIFE.BISON)){
        this.food += 5;
    }
    else if(chooseAnimal.equals(WILDLIFE.DEER)){
        this.food += 3;
    }
    else if(chooseAnimal.equals(WILDLIFE.RABBIT)){
        this.food += 1;
    }
    System.out.println("There are plentiful " + chooseAnimal + " around these parts.");
}
public void eat(){
    if (this.food == 1) {
        this.food -= 1;
        this.isHealthy = false;
    } else if (this.food < 1) {
        this.food = 0;
        this.isHealthy = false;
    } else {this.food -= 2;}
}
 public int giveFood(Traveler traveler, int numOfFoodUnits){
    if (this.food > numOfFoodUnits){
        this.food -= numOfFoodUnits;
    }
     return traveler.food += numOfFoodUnits;
 }


  
  
}

