package com.kenzie.app;

import java.util.Arrays;
import java.util.Locale;

public class Main {
    /* To run this assignment, select Run --> Run 'Main" from the file menu at the top of IntelliJ IDEA */

    /********************************/
    /* Writing and Calling Methods  */
    /*******************************/
    // ----------------------------------------------------------------------
    // Complete the Following Exercises
    // ----------------------------------------------------------------------


    // Create helper methods to help you complete each Exercise
    // Your methods should all be public static methods

    /*******************************/
    //      EXERCISE ONE
    /******************************/

    //Here we will walk through writing a method and calling it to find the longest word in a String sentence.
    //Write static findLongestWord()

    public static String findLongestWord(String sentence){
        //First convert your sentence to an array of words
        //Loop through the words and find the longest word
        //Store the longest word in a variable
        //Return the longest word stored in that variable
        //HINT: if the multiple words have the same length return the first longest
        String longestWord = "";

        //Write your code below
        String[] sentenceArray = sentence.split(" ");

        for (int i = 0; i < sentenceArray.length; i++) {
            if (sentenceArray[i].length() > longestWord.length()) {
                longestWord = sentenceArray[i];
            }
        }

        return longestWord;
    }

    public static void exerciseOne(){
        System.out.println("Exercise 1");
        String sentence = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        //Now we call findLongestWord inside the method exerciseOne.
        String longestWord = findLongestWord(sentence);
        System.out.println("The longest word is: " + longestWord);

    }
    //To run exerciseOne to test your code scroll down and run main()


    /*******************************/
    //      EXERCISE TWO
    /******************************/

    //Now write a method to find the shortest word. It will be very similar to findLongestWord
    //For this exercise we provide a little less scaffolding
    //HINT: if the multiple words have the same length return the first shortest word
    public static String findShortestWord(String sentence){
        //Fill out a method for finding the shortest word
        String shortestWord = sentence;

        //Write your code below
        String[] sentenceArray = sentence.split(" ");

        for (int i = 0; i < sentenceArray.length; i++) {
            if (sentenceArray[i].length() < shortestWord.length()) {
                shortestWord = sentenceArray[i];
            }
        }
        return shortestWord;
    }

    public static void exerciseTwo(){
        System.out.println("Exercise 2");
        String sentence = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        //Call findShortestWord()  to print out the shortest word
        //Print out the shortest word in a message:  "The shortest word is: {shortest word}"
        //Do not include the braces {} in the output
        //Write your code below
        System.out.println("The shortest word is: " + findShortestWord(sentence));


    }

    /*******************************/
    //      EXERCISE THREE
    /******************************/
    //Now we will have you write a method that returns the length of the longest word.
    //HINT:  You will need to return an int!

    //Write your method here
    public static int longestWordLength(String sentence){
        int longestLength = findLongestWord(sentence).length();

        return longestLength;

    }



    //Now call your method in exerciseThree()
    public static void exerciseThree(){
        System.out.println("Exercise 3");
        String sentence = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        // Print the longest word to the console like this "The length of the longest word is: {length}"
        // Do not include curly braces in your output
        //Write your code below
        System.out.println("The length of the longest word is: " + longestWordLength(sentence));


    }

    /*******************************/
    //      EXERCISE FOUR
    /******************************/

    //Write a static countLetter() method that takes two arguments, a letter and a string. The method will count
    //how many times the letter appears in the string. Count both upper and lower case versions of the letter
    //Hint: Even though the letter is a single character long, you can still use it as a String parameteter
    //Write your method below:
    public static int countLetter(String sentence, char character) {
        char[] letterArray = sentence.toLowerCase().replace(" ", "").toCharArray();
        int letterCount = 0;
        for (int i = 0; i < letterArray.length; i++){
            if (letterArray[i] == character ){
                letterCount++;
            }
        }
        return letterCount;
    }




    //Use your count letter in the exerciseFour method.

    public static void exerciseFour(){
        System.out.println("Exercise 4");
        String sentence = "The best thing about a boolean is even if you are wrong you are only off by a bit";
        // Call the same countLetter() method for each vowel "a", "e", "i", "o" and "u" on the sentence, upper or lower case
        // Print out the total number of each vowel
        // Then add all the totals together and display the total Count
        // Print out the total together
        //HINT USE System.out.println() for each. You can declare additional variables to store values
        //It should look something like this:
        // "Number of a's: 6"
        // ...
        // ... {other vowels}
        // ...
        // Total number of vowels: 28
        int allVowels = countLetter(sentence, 'u') + + countLetter(sentence, 'o') + + countLetter(sentence, 'i') + countLetter(sentence, 'e') + countLetter(sentence, 'a');
        System.out.println("Number of a's: " + countLetter(sentence, 'a'));
        System.out.println("Number of e's: " + countLetter(sentence, 'e'));
        System.out.println("Number of i's: " + countLetter(sentence, 'i'));
        System.out.println("Number of o's: " + countLetter(sentence, 'o'));
        System.out.println("Number of u's: " + countLetter(sentence, 'u'));
        System.out.println("Total number of vowels: " + allVowels);




    }

    public static void main(String[] args) {
        /* To run this assignment, select Run --> Run 'Main" from the file menu at the top of IntellliJ IDEA */
        exerciseOne();
        exerciseTwo();
        exerciseThree();
        exerciseFour();
    }

}
