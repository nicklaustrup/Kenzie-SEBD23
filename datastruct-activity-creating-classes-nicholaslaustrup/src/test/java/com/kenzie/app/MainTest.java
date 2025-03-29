package com.kenzie.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void rollDice() {
        int result = Main.rollDice();
        assert(result >= 2 && result <= 12) ;
        result = Main.rollDice();
        assert(result >= 2 && result <= 12) ;
        result = Main.rollDice();
        assert(result >= 2 && result <= 12) ;
    }

    @Test
    public void roll1000Dice() {
        int[] results = Main.roll1000Times();
        assertEquals(13, results.length, "There are the right number of elements"); ;
        for (int i = 2; i < results.length; i++) {
            assertEquals(true, results[i] > 0, "Every number 2-12 is greater than 0");
        }
        int rollsTotal = 0;
        for (int i = 0; i < results.length; i++){
            rollsTotal += results[i];
        }
        assertTrue(rollsTotal == 1000, "Rolled the correct number of dice");
    }

    @Test
    public void roll1000DiceReRolling1s() {
        int[] results = Main.roll1000TimesReRolling1s();
        assertEquals(13, results.length, "There are the right number of elements"); ;
        for (int i = 4; i < results.length; i++) {
            assertTrue(results[i] > 0, "Every number 2-12 is greater than 0");
        }
        assertEquals(0, results[2], "2 should not be rolled");
        assertEquals(0, results[3], "3 should not be rolled");

        int rollsTotal = 0;
        for (int result : results) {
            rollsTotal += result;
        }
        assertEquals(1000, rollsTotal, "Rolled the correct number of dice");

    }

    @Test
    public void runMain() {
        boolean crashed = false;
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            crashed = true;
        }

        assertEquals(false, crashed, "App can run without crashing") ;
    }
}

