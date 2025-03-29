package com.kenzie.app.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() {
        //Arrange
        //inputs - define the varialbes of input and expected output
        Calculator calculator = new Calculator();
        int input1 = 1;
        int input2 = 2;

        int expectedOutput = 3;

        //Act
        //Actual Output
        int actualOutput = calculator.add(input1, input2);


        //Assemble
        //Code to test this vs that
        assertEquals(expectedOutput, actualOutput);


    }

    @Test
    void subtract() {
        //Arrange
        //inputs - define the varialbes of input and expected output
        Calculator calculator = new Calculator();
        int input1 = 1;
        int input2 = 2;

        int expectedOutput = 1;

        //Act
        //Actual Output
        int actualOutput = calculator.subtract(input2, input1);


        //Assemble
        //Code to test this vs that
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void multiply() {
        //Arrange
        //inputs - define the varialbes of input and expected output
        Calculator calculator = new Calculator();
        int input1 = 2;
        int input2 = 3;

        int expectedOutput = 6;

        //Act
        //Actual Output
        int actualOutput = calculator.multiply(input1, input2);


        //Assemble
        //Code to test this vs that
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void divide() {
        //Arrange
        //inputs - define the varialbes of input and expected output
        Calculator calculator = new Calculator();
        int input1 = 6;
        int input2 = 3;

        int expectedOutput = 2;

        //Act
        //Actual Output
        int actualOutput = calculator.divide(input1, input2);


        //Assemble
        //Code to test this vs that
        assertEquals(expectedOutput, actualOutput);
    }
}