package com.kenzie.app;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

public class Main {
    /***********************/
    /* Manipulating Strings        */
    /***********************/

    // ----------------------------------------------------------------------
    // Complete the Following Exercises
    // ----------------------------------------------------------------------

    // Each one should be displayed in this manner:
    // System.out.println("Exercise 0");
    // System.out.println(answer);
    // Result:
    // Exercise 0
    // {this,is,the,answer}


    public static void exerciseOne(){
        String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        System.out.println("Exercise 1");
        // Write code that uses "substring" to return a string with the first 14 characters from "bestThing".
        // Display your result using System.out.println(str);
        // Write Your Code Here
        System.out.print(bestThing.substring(0, 14));
        System.out.println();

    }
    public static void exerciseTwo(){
        String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        System.out.println("Exercise 2");
        // Write code that uses "substring" to return a string with the last 12 characters from "bestThing".
        // Display your result using System.out.println(str);
        // Write Your Code Here

        System.out.println(bestThing.substring(bestThing.length() - 12));

    }
    public static void exerciseThree(){
        String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        System.out.println("Exercise 3");
        // Write code that uses "substring" to return a string with the characters between the 3rd and 5th position of "bestThing"
        // (i.e., " b").
        // Display your result using System.out.println(str);
        // Write Your Code Here
        System.out.println(bestThing.substring(3, 5));

    }
    public static void exerciseFour(){
        String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        System.out.println("Exercise 4");
        // Write code that uses "substring" to return a string with the characters between the 23rd and 38th position of "bestThing"
        // (i.e., "boolean is even").
        // Display your result using System.out.println(str);
        // Write Your Code Here
        System.out.println(bestThing.substring(23, 38));

    }
    public static void exerciseFive(){
        String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        System.out.println("Exercise 5");
        // Write code that returns an array that contains each word in "bestThing"
        // Display your result using the call: Arrays.toString(wordArray);
        // Write Your Code Here
        String[] bestThingArray = bestThing.split(" ");
        System.out.print(Arrays.toString(bestThingArray));
        System.out.println();

    }
    public static void exerciseSix() {
        String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        System.out.println("Exercise 6");
        // Write code that finds and returns the index of "only" in "bestThing" once turned into an array
        // Display your result using System.out.println(index);
        // Write Your Code Here
        String[] bestThingArray = bestThing.split(" ");
        int onlyIndex = 0;
        for (int i = 0; i < bestThingArray.length; i++){
           if (bestThingArray[i].equals("only")){
               onlyIndex = i;
           }
        }

        System.out.println(onlyIndex);

    }
    public static void exerciseSeven(){
        String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        System.out.println("Exercise 7");
        // Write code that converts the bestThing string to an array and then finds
        // and returns the index of the last word in "bestThing".
        // Display your result using System.out.println(index);
        // Write Your Code Here
        String[] bestThingArray = bestThing.split(" ");
        int finalIndex = bestThingArray.length -1;
        System.out.println(finalIndex);
        }


    public static void exerciseEight(){
        String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        System.out.println("Exercise 8");
        // Write code that finds and returns an array with all the words in "bestThing" that start with a "b".
        // Display your result using the call: Arrays.toString(bWordArray);
        // Hint: You will need two loops to do this without hard-coding
        // Write Your Code Here
        String[] WordArray = bestThing.split(" ");
        int bIndex = 0;
        for (int i = 0; i < WordArray.length; i++){
            if (WordArray[i].startsWith("b"))
                bIndex++;
        }

       String[] bWordArray = new String [bIndex];
        int bWords = 0;
        for (int i = 0; i < WordArray.length; i++){
            if (WordArray[i].startsWith("b")) {
               bWordArray[bWords] = WordArray[i];
               bWords++;
            }
        }

        System.out.println(Arrays.toString(bWordArray));

    }
    public static void exerciseNine(){
        String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        System.out.println("Exercise 9");
        // Write code converts the String "bestThing" into an array of characters. Do not include spaces.
        // Display your result using the call: Arrays.toString(lettersArray);
        // Write Your Code Here
        char[] lettersArray = bestThing.replace(" ", "").toCharArray();
        System.out.print(Arrays.toString(lettersArray));
        System.out.println(        );




    }
    public static void exerciseTen() {
        String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        System.out.println("Exercise 10");
        // Write code converts the String "bestThing" into an array of characters with spaces removed and then use for-each to
        // loop through the array and count the number of times the letter "a" appears.
        // Display your result using: System.out.println(count);
        // Write Your Code Here
        char[] charArray = bestThing.replace(" ", "").toCharArray();
        int counter = 0;
        for (char aCounter : charArray) {
            if (aCounter == 'a' ) {
                counter++;
            }
        }
            System.out.print(counter);
        System.out.println();
    }



    public static void exerciseEleven(){
        String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        System.out.println("Exercise 11");
        // Write code converts the String "bestThing" into an array of characters and then use for-each to
        // loop through the array and count the number of times the letter "t" in uppercase or lowercase appears.
        // Display your result using: System.out.println(count);
        // Display your result using: System.out.println(count);
        // Write Your Code Here
        char[] charArray = bestThing.toCharArray();
        int tCount = 0;
        for (char tCounter : charArray){
            if (tCounter == 't') {
                tCount++;
            }
            if (tCounter ==  'T') {
                tCount++;
            }
        }
        System.out.println(tCount);;
        System.out.println();
    }


    public static void main(String[] args) {
        /* To run this assignment, select Run --> Run "Main" from the file menu at the top of IntelliJ IDEA */
        exerciseOne();
        exerciseTwo();
        exerciseThree();
        exerciseFour();
        exerciseFive();
        exerciseSix();
        exerciseSeven();
        exerciseEight();
        exerciseNine();
        exerciseTen();
        exerciseEleven();
    }

}
