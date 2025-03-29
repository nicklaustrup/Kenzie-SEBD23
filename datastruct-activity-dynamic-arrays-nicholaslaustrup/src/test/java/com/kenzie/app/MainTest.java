package com.kenzie.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {
    private ArrayList<String> testInput;

    @BeforeEach
    public void setTestInput() {
        testInput = new ArrayList<>(Arrays.asList("waffles", "apples", "oatmeal", "five hour energy shot"));
    }

    @AfterEach
    public void clearTestInput() {
        testInput.clear();
    }

    // Exercise One - Create an Arraylist and add each of the given words to it.
    @Test
    public void test_createFoodsList() {
        ArrayList<String> output = Main.createFoodsList(testInput.get(0), testInput.get(1), testInput.get(2), testInput.get(3));
        assertEquals(testInput.toString(), output.toString());
    }


    // Exercise Two - Find the size of the array
    @Test
    public void test_findTheSizeOfArrayList() {
        assertEquals(4, Main.findTheSizeOfArrayList(testInput));
        testInput.add("changing the size");
        assertEquals(5, Main.findTheSizeOfArrayList(testInput));
    }

    // Exercise Three - Access the element at the index
    @Test
    public void test_accessTheElementAtParticularIndex() {
        assertEquals("oatmeal", Main.accessTheElementAtParticularIndex(testInput, 2));
        testInput.add("changing the size");
        assertEquals("changing the size", Main.accessTheElementAtParticularIndex(testInput, 4));
    }
    
    // Exercise Four - Count the number
    @Test
    public void test_countNumOfCakes() {
        testInput.add("Cake");
        testInput.add("Cake");
        testInput.add("Pie");
        assertEquals(2, Main.countNumOfCakes(testInput));
        testInput.add("Cake");
        assertEquals(3, Main.countNumOfCakes(testInput));
    }

    // Exercise Five - modify the element at the index
    @Test
    public void test_modifyTheElementAtParticularIndex() {
        Main.modifyTheElementAtParticularIndex(testInput, 2, "hashbrowns");
        assertEquals("hashbrowns", testInput.get(2));
    }

    // Exercise Six - sorting the array
    @Test
    public void test_sortTheArrayList() {
        Main.sortTheArrayList(testInput);
        assertEquals(
                "[apples, five hour energy shot, oatmeal, waffles]",
                testInput.toString());
    }

    // Exercise Seven - convert to string array
    @Test
    public void test_convertArrayListToStringArray() {
        assert(Arrays.asList(Main.convertArrayListToStringArray(testInput)).contains("apples"));
        assert(Arrays.asList(Main.convertArrayListToStringArray(testInput)).contains("waffles"));
        assert(Arrays.asList(Main.convertArrayListToStringArray(testInput)).contains("oatmeal"));
        assert(Arrays.asList(Main.convertArrayListToStringArray(testInput)).contains("five hour energy shot"));
    }

    // Exercise Eight - convert string array to arraylist
    @Test
    public void test_convertStringArrayToArrayList() {
        String[] testArray = { "apples", "waffles", "oatmeal", "five hour energy shot" };
        assert(Main.convertStringArrayToArrayList(testArray).contains("apples"));
        assert(Main.convertStringArrayToArrayList(testArray).contains("waffles"));
        assert(Main.convertStringArrayToArrayList(testArray).contains("oatmeal"));
        assert(Main.convertStringArrayToArrayList(testArray).contains("five hour energy shot"));
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

