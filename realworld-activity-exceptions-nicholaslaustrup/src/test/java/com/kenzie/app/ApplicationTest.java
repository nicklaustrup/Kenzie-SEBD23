package com.kenzie.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


// DO NOT attempt to run these tests until you have resolved the compiler issues!
public class ApplicationTest {

    @DisplayName("Test: isInputValid handles valid input")
    @Test
    public void testIsInputValid_ValidInput(){
        int inputNumber = 2;
        assertTrue(Application.isInputValid(inputNumber));
    }

    @DisplayName("Test: isInputValid throws IllegalArgumentException")
    @Test
    public void testIsInputValid_InvalidInput(){
        int inputNumber = 24;
        assertThrows(IllegalArgumentException.class, () -> {
            Application.isInputValid(inputNumber);
        });
    }

    @DisplayName("Test: convertToString handles valid input")
    @Test
    public void testConvertToString_ValidInput(){
        String input = "3";
        assertEquals(3, Application.convertStringInput(input));
    }

    @DisplayName("Test: convertToString throws NumberFormatException")
    @Test
    public void testConvertToString_InvalidInput(){
        String input = "apples";
        assertThrows(NumberFormatException.class, () -> {
            Application.convertStringInput(input);
        });
    }

    @DisplayName("Test: checkForEmptyString throws CustomEmptyStringException")
    @Test
    public void testCustomException(){
        String input = "";
        assertThrows(CustomEmptyStringException.class, ()-> {
            Application.checkForEmptyString(input);
        });
    }
}
