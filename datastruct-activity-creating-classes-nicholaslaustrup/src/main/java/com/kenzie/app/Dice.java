package com.kenzie.app;

import java.util.Random;

public class Dice {
    //define class variables here
    public int die1;
    public int die2;
//    private int diceRoll




    //define class methods here
    public void roll(){

        // roll dice 1
        Random diceRoll = new Random();
        this.die1 = (diceRoll.nextInt(6) + 1);
        // roll dice 2
        Random diceRoll2 = new Random();
        this.die2 = (diceRoll2.nextInt(6) + 1);
        //increment diceRolls
//        this.diceRolls++;

    }

    public int getTotal(){
        roll();
        int total = this.die1 + this.die2;
        return total;
    }

    public int getDice1(){
        return this.die1;
    }

    public int getDice2 (){
        return this.die2;
    }



}
