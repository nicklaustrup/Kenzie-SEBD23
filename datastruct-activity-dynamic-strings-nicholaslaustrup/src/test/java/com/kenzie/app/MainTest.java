package com.kenzie.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {

    // Create a stringbuilder object initialized with a name
    @Test
    public void test_createStringBuilderWithName() {
        StringBuilder result = Main.createStringBuilderWithName("Dread Pirate Roberts");
        assertEquals("Dread Pirate Roberts", result.toString());
    }


    // Combine multiple strings to form a comma separated list
    @Test
    public void test_createCommaSeparatedList() {
        String result = Main.createCommaSeparatedList(
            "Inigo Montoya",
            "Humperdinck",
            "Tyrone Rugen",
            "Buttercup"
        );

        assertEquals( "Inigo Montoya, Humperdinck, Tyrone Rugen, Buttercup", result);
    }

    // Replace substring with input
    @Test
    public void test_replaceTextInString() {
        String substring = "As you wish";
        String replacementValue = "I love you";
        String inputText = "That day, she was amazed to discover that when he was saying \"As you wish,\" what he meant was, \"I love you.\" And even more amazing was the day she realized she truly loved him back";
        String expectedResult = "That day, she was amazed to discover that when he was saying \"I love you,\" what he meant was, \"I love you.\" And even more amazing was the day she realized she truly loved him back";
        String output = Main.replaceTextInString(
                inputText,
                substring,
                replacementValue);
        assertEquals(expectedResult, output);
    }

    // Create a sentence from an array
    @Test
    public void test_createFavoriteCharactersSentence() {
        String[] characters = new String[] {"Westley", "Buttercup", "Inigo Montoya", "Dread Pirate Roberts" };
        String result = Main.createFavoriteCharactersSentence(characters);
        String expectedResult = "My favorite characters are Westley, Buttercup, Inigo Montoya, and Dread Pirate Roberts.";
        assertEquals(expectedResult, result);
    }


    // Does the program run?
    // This should pass automatically

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
