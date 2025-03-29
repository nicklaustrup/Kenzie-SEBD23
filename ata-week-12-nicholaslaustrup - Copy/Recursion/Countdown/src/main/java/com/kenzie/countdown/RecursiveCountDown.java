package com.kenzie.countdown;

public class RecursiveCountDown {

    /**
     * This recursive method will return a string consisting of all the integers from the target
     * down to zero. ex: target=3 will return "3210"
     * @param target - this is the number we are counting down from
     * @return - this is a String that has combined all the numbers from target down to 0.
     */
    public static String countDown(int target) {
        //PARTICIPANTS: replace this placeholder line with your implementation

        if (target == 0) {
            return Integer.toString(0);
        } else {
            return Integer.toString(target) + countDown(target -1);
        }
    }
}
