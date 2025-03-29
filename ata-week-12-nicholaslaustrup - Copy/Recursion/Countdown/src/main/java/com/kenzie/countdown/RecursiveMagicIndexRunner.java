package com.kenzie.countdown;

import java.util.Arrays;
import java.util.List;

public class RecursiveMagicIndexRunner {


    /**
     * Test main to output the results of our CountDown recursion method.
     * @param args not used
     */
    public static void main(String[] args) {

        List<Integer> testInput = Arrays.asList(-1, 0, 2, 3, 7, 22);

        System.out.println(RecursiveMagicIndex.magicIndex(testInput));
        //Example: The output for testInput should be 3;
    }
}
