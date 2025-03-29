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
    public void test_exerciseOne(){
        Main.exerciseOne();
        assertThat(outContent.toString(), containsString("The best thing"));
    }

    @Test
    public void test_exerciseTwo(){
        Main.exerciseTwo();
        assertThat(outContent.toString(), containsString("off by a bit"));
    }

    @Test
    public void test_exerciseThree(){
        Main.exerciseThree();
        assertThat(outContent.toString(), containsString(" b"));
    }

    @Test
    public void test_exerciseFour(){
        Main.exerciseFour();
        assertThat(outContent.toString(), containsString("boolean is even"));
    }


    @Test
    public void test_exerciseFive(){
        Main.exerciseFive();
        assertThat(outContent.toString(), containsString("[The, best, thing, about, a, boolean, is, even, if, you, are, wrong, you, are, only, off, by, a, bit]"));
    }

    @Test
    public void test_exerciseSix(){
        Main.exerciseSix();
        assertThat(outContent.toString(), containsString("14"));
    }

    @Test
    public void test_exerciseSeven(){
        Main.exerciseSeven();
        assertThat(outContent.toString(), containsString("18"));
    }

    @Test
    public void test_exerciseEight(){
        Main.exerciseEight();
        assertThat(outContent.toString(), containsString("[best, boolean, by, bit]"));
    }
    @Test
    public void test_exerciseNine(){
        Main.exerciseNine();
        assertThat(outContent.toString(), containsString("[T, h, e, b, e, s, t, t, h, i, n, g, a, b, o, u, t, a, b, o, o, l, e, a, n, i, s, e, v, e, n, i, f, y, o, u, a, r, e, w, r, o, n, g, y, o, u, a, r, e, o, n, l, y, o, f, f, b, y, a, b, i, t]"));
    }

    @Test
    public void test_exerciseTen(){
        Main.exerciseTen();
        assertThat(outContent.toString(), containsString("6"));
    }

    @Test
    public void test_exerciseEleven(){
        Main.exerciseEleven();
        assertThat(outContent.toString(), containsString("5"));
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
