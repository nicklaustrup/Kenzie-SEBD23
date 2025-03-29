package com.kenzie.countdown;

public class RecursiveCountDownRunner {

    /**
     * Test main to output the results of our CountDown recursion method.
     * @param args not used
     */
    public static void main(String[] args) {
        int target = 10;
        System.out.println(RecursiveCountDown.countDown(target));
        //Example: The output for a target of 3 should be "3210"
    }
}
