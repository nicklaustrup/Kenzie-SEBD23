package com.kenzie.app;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
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
    public void test_exerciseOne(){
        Main.exerciseOne();
        assertThat(outContent.toString(), containsString("The longest word is: boolean"));
    }

    @Test
    public void test_exerciseTwo(){
        Main.exerciseTwo();
        assertThat(outContent.toString(), containsString("The shortest word is: a"));
    }

    @Test
    public void test_exerciseThree(){
        Main.exerciseThree();
        assertThat(outContent.toString(), containsString("The length of the longest word is: 7"));
    }

    @Test
    public void test_exerciseFour(){
        Main.exerciseFour();
        assertThat("Incorrect number of a's", outContent.toString(), containsString("Number of a's: 6"));
        assertThat("Incorrect number of e's", outContent.toString(), containsString("Number of e's: 7"));
        assertThat("Incorrect number of i's", outContent.toString(), containsString("Number of i's: 4"));
        assertThat("Incorrect number of o's", outContent.toString(), containsString("Number of o's: 8"));
        assertThat("Incorrect number of u's",outContent.toString(), containsString("Number of u's: 3"));
        assertThat("Incorrect total number vowels", outContent.toString(), containsString("Total number of vowels: 28"));
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
