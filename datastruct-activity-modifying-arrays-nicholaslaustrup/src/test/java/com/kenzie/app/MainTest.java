package com.kenzie.app;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private ArrayList<String> testInput;

    // Set up streams to test console input and output
    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeEach
    public void setTestInput() {
        testInput = new ArrayList<>(Arrays.asList("waffles", "apples", "oatmeal", "five hour energy shot"));
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void clearTestInput() {
        testInput.clear();
    }


    // Exercise One
    //      - Increment each value by 1
    //      - This modifies the original array
    @Test
    public void testOne() {
        int[] original = {-1, 6, 10, 141, -10};
        int[] numbers = {-1, 6, 10, 141, -10};
        assertEquals(original.length, numbers.length);
        Main.incrementEveryNumber(numbers);
        for(int i = 0; i < numbers.length; i++) {
            assertEquals(numbers[i], original[i] + 1);
        }
    }

    // Exercise Two
    //      - fill with random positive numbers between 1-27
    //      - this should modify the original array
    @Test
    public void testTwo() {
        int[] numbers = new int[20];
        Main.fillWithRandomNumbers(numbers);
        for(int number : numbers) {
            assert(number > 0);
        }
    }


    // Exercise Three
    //      - Remove every number in the array less than 10
    //      - Return a new array
    @Test
    public void testThree() {
        int[] input = {-1, 6, 10, 141, -10};
        int[] expected = {10, 141};
        int[] actual = Main.removeNumbersBelowTen(input);
        assertNotEquals(input, actual);
        Arrays.sort(expected);
        Arrays.sort(actual);
        assert(Arrays.equals(expected, actual));
    }

    // Exercise Four
    //      - Sum the numbers
    @Test
    public void testFour() {
        int[] input = {-1, 6, 10, 141, -10};
        assertEquals(146 ,Main.addAllNumbers(input));
    }

    // Exercise Five
    //      - Replace instances of input word with replacement value
    @Test
    public void testFive() {
        String[] input = "HOLD ON HOLD ON HOLD ON...HER SISTER WAS A WITCH RIGHT? AND WHAT WAS HER SISTER? A PRINCESS, THE WICKED WITCH OF THE EAST BRO. YOU'RE GONNA LOOK AT ME AND YOU'RE GONNA TELL ME THAT I'M INCORRECT? AM I INCORRECT? SHE WORE A CROWN AND SHE CAME DOWN IN A BUBBLE, DOUG.".split(" ");
        String expected = "HOLD ON HOLD ON HOLD ON...HER SISTER WAS A WITCH RIGHT? AND WHAT WAS HER SISTER? A PRINCESS, THE WICKED WITCH OF THE EAST BRO. YOU'RE GONNA LOOK AT ME AND YOU'RE GONNA TELL ME THAT I'M WRONG? AM I WRONG? SHE WORE A CROWN AND SHE CAME DOWN IN A BUBBLE, DOUG.";
        Main.replaceAll(input, "INCORRECT?", "WRONG?");
        assertEquals(expected,
                String.join(" ", input));
    }

    // Exercise Six
    //      - Reverse the order of the elements in the array
    @Test
    public void testSix() {
        int[] input = {1,4,5,6,9};
        int[] expected = {9,6,5,4,1};
        Main.reverse(input);
        for(int i = 0; i < input.length; i++) {
            assertEquals(expected[i], input[i]);
        }
    }

    // Exercise Seven
    //      - Reverse the order of the elements in the array
    @Test
    public void testSeven() {
        String[] input = "HOLD ON HOLD ON HOLD ON...HER SISTER WAS A WITCH RIGHT? AND WHAT WAS HER SISTER? A PRINCESS, THE WICKED WITCH OF THE EAST BRO. YOU'RE GONNA LOOK AT ME AND YOU'RE GONNA TELL ME THAT I'M INCORRECT? AM I INCORRECT? SHE WORE A CROWN AND SHE CAME DOWN IN A BUBBLE, DOUG.".split(" ");
        String[] reversed = {"DOUG.","BUBBLE,","A","IN","DOWN","CAME","SHE","AND","CROWN","A","WORE","SHE","INCORRECT?","I","AM","INCORRECT?","I'M","THAT","ME","TELL","GONNA","YOU'RE","AND","ME","AT","LOOK","GONNA","YOU'RE","BRO.","EAST","THE","OF","WITCH","WICKED","THE","PRINCESS,","A","SISTER?","HER","WAS","WHAT","AND","RIGHT?","WITCH","A","WAS","SISTER","ON...HER","HOLD","ON","HOLD","ON","HOLD"};
        Main.reverseString(input);
        for(int i = 0; i < input.length; i++) {
            assertEquals(reversed[i], input[i]);
        }
    }

    // Exercise Eight
    //      - Return an array of 3 random elements from the array
    //      - The input array should be shuffled in place
    @Test
    public void testEight() {
        int[] original = {1,4,9,5,6,9,-10,9,6,-15};
        int[] input = {1,4,9,5,6,9,-10,9,6,-15};
        int[] expectedOutput = {1,4,9,5,6,9,-10,6,-15};
        int[] output = Main.removeLastOccurrence(input, 9);
        assertEquals(original.length - 1, output.length);
        assertEquals(Arrays.toString(expectedOutput), Arrays.toString(output));
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
