package com.kenzie.app;

import java.util.Arrays;

public class Main {

    public static int rollDice() {
        // Your Code Here
        Dice diceRoll = new Dice();
        int rolled = diceRoll.getTotal();
        System.out.println(diceRoll.getDice1());
        System.out.println(diceRoll.getDice2());


        return rolled;
    }

    public static int[] roll1000Times() {
        int[] results = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        // Your Code Here
        Dice diceRoll = new Dice();
        int diceResult = 0;

        for (int i = 0; i < 1000; i++) {
            diceResult = diceRoll.getTotal();
            results[diceResult]++;
        }



        return results;
    }

    public static int[] roll1000TimesReRolling1s() {
        int[] results = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        // Now, the dice 1000 times again.
        // But, re-roll both dice any time either die is a 1.
        // Your Code Here
        Dice diceRoll = new Dice();
        int diceResult = 0;

        for (int i = 0; i < 1000;) {
            diceResult = diceRoll.getTotal();
            if (diceResult > 3) {
                results[diceResult]++;
                i++;
            }
        }

        return results;
    }

    public static void main(String[] args) {
	    // You should not need to modify the code in this method.  Put your code in the two methods above.
        System.out.println("I rolled the dice and got: " + rollDice());

        int[] results = roll1000Times();

        System.out.println("Rolling 1000 Dice...");
        System.out.println("Results: ");
        for (int i = 2; i < results.length; i++) {
            System.out.println(i + ": " + results[i]);
        }

        results = roll1000TimesReRolling1s();

        System.out.println("Rolling 1000 Dice while re-rolling 1s...");
        System.out.println("Results: ");
        for (int i = 2; i < results.length; i++) {
            System.out.println(i + ": " + results[i]);
        }
    }
}
