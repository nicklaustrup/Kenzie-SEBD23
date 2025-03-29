package com.kenzie.app;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @BeforeEach
    public void setTestInput() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void test_ExerciseOne_GreaterThanTen(){
        Main.Exercise_One(11);
        assertThat("Make sure your if statement is correct", outContent.toString(), containsString("It is bigger than 10!"));
    }

    @Test
    public void test_ExerciseTwo_Blue(){
        Main.Exercise_Two("blue");
        assertThat("Check that the color is blue and print out the correct message", outContent.toString(), containsString("The sky is blue."));
    }

    @Test
    public void test_ExerciseTwo_Red(){
        Main.Exercise_Two("red");
        assertThat("Check that the color is red and print out the correct message", outContent.toString(), containsString("The flower is red."));
    }

    @Test
    public void test_ExerciseTwo_Green(){
        Main.Exercise_Two("green");
        assertThat("Check that the color is green and print out the correct message", outContent.toString(), containsString("The leaf is green."));
    }
    @Test
    public void test_ExerciseTwo_Other(){
        Main.Exercise_Two("black");
        assertThat("Make sure you have the base case covered when its not blue, red, or green", outContent.toString(), containsString("It is a different color."));
    }

    @Test
    public void test_ExerciseThree_Even(){
        Main.Exercise_Three(2);
        assertThat("Make sure your if statement is correct for evens and odds", outContent.toString(), containsString("The number is even."));

    }

    @Test
    public void test_ExerciseThree_Odd(){
        Main.Exercise_Three(3);
        assertThat("Make sure your if statement is correct for evens and odds", outContent.toString(), containsString("The number is odd."));
    }

    @Test
    public void test_ExerciseFour_16(){
        Main.Exercise_Four(16);
        assertThat("Make sure your if statement is correct for 16", outContent.toString(), containsString("16!"));
    }

    @Test
    public void test_ExerciseFour_LessThan10(){
        Main.Exercise_Four(9);
        assertThat("Make sure your if statement is correct for less than 10", outContent.toString(), containsString("10 or less!"));
    }

    @Test
    public void test_ExerciseFour_10(){
        Main.Exercise_Four(10);
        assertThat("Make sure your if statement is correct for 10", outContent.toString(), containsString("10 or less!"));
    }

    @Test
    public void test_ExerciseFour_GreaterThan20(){
        Main.Exercise_Four(21);
        assertThat("Make sure your if statement is correct for greater than 20", outContent.toString(), containsString("20 or greater!"));
    }

    @Test
    public void test_ExerciseFour_20(){
        Main.Exercise_Four(20);
        assertThat("Make sure your if statement is correct for 20", outContent.toString(), containsString("20 or greater!"));
    }

    @Test
    public void test_ExerciseFour_Between10and20(){
        Main.Exercise_Four(15);
        assertThat("Make sure your if statement is correct for between 10 and 20", outContent.toString(), containsString("Between 10 and 20!"));
    }

    @Test
    public void runMainWithoutCrashing() {
        boolean crashed = false;
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            crashed = true;
        }

        assertEquals(false, crashed, "App can run without crashing") ;
    }
}
