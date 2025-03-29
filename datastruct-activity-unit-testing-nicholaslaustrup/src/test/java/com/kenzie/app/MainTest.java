package com.kenzie.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MainTest {

    //Write a test method to check that the addGreeting method contains "Hello"
    @Test
    public void testAddGreetingContainsHello(){
        String input = "";
        String expectedOutput = "Hello " + input + "!";
        String actualOutput = Main.addGreeting(input);

        assertEquals(actualOutput, expectedOutput);
    }

    //Write a test method to check that the addNumbers method works correctly
    @Test
    public void testAddNumbers(){
        int input1 = 1;
        int input2 = 2;
        int expectedOutput = input1 + input2;
        int actualOutput = Main.addNumbers(input1, input2);

        assertEquals(expectedOutput, actualOutput);

    }

    //Write a test method to check that the countCharacter method can be called with letters
    @Test
    public void testCountCharacterMatchLetters() {
        String inputString = "Alphabet";
        char inputChar = 'A';
        int actualOutput = Main.countCharacter(inputChar, inputString);
        int expectedOutput = 2;

        assertEquals(expectedOutput, actualOutput);

    }

    //Test that countCharacter can be used to match punctuation characters
    @Test
    public void testCountCharacterMatchPunctuation() {
        String inputString = "hello! world!!!";
        char inputChar = '!';
        int actualOutput = Main.countCharacter(inputChar, inputString);
        int expectedOutput = 4;

        assertEquals(expectedOutput, actualOutput);
    }

    //Edge case: Test that countCharacter can handle an empty string
    @Test
    public void testCountCharactersEmptyString() {
        String inputString = "";
        char inputChar = 'a';
        int actualOutput = Main.countCharacter(inputChar, inputString);
        int expectedOutput = 0;

        assertEquals(expectedOutput, actualOutput);
    }

    //Edge case: Test that countCharacter can handle case-insensitive matching
    //Write this unit test first. When countCharacter fails this test -- then go and fix the countCharacter method in Main.java
    @Test
    public void testCountCharacterMatchAnyCase() {
        String inputString = "Hello World hi";
        char inputChar = 'H';
        int actualOutput = Main.countCharacter(inputChar, inputString);
        int expectedOutput = 2;

        assertEquals(expectedOutput, actualOutput);
    }


}

