package com.kenzie.app;

import java.util.Scanner;

public class Main {

    // In this assessment, you will be evaluated on how well you organize your code into methods 
    // as well as how well you develop methods in order to achieve the requirements. 
    
    //Suggested Methods: 
    // Create a countCharacter() method
    // to count how many times a specific character appears in a string
    // Characters can be letters or punctuation
    // Hint: This can be done by taking in the character as a char or as a String
    // - vowels, punctuation
    //Hint: return an int

    /*                          Character Counter Method                           */
    public static int countCharacter(String inputString, char inputChar){
        char[] inputCharsArray = inputString.toLowerCase().toCharArray();
        int indexPunct = 0;

        for (int i = 0; i < inputCharsArray.length; i++) {
            if (inputCharsArray[i] == inputChar){
                indexPunct++;
            }


        }

        return indexPunct;
    }




    /*                          Letter Counter Method                           */
    //Create a method that counts the number of characters in a string
    public static int countLetter(String inputString){
        char[] inputStringChars = inputString.toCharArray();
        int letterCount = 0;

        for (int i = 0; i < inputStringChars.length; i++) {
            if (letterCount < inputStringChars.length) {
                letterCount++;
            }
        }

        return letterCount;
    }



    /*                         Word Counter Method                           */
    //Create a method to count the number of words in a string
    public static int countWords(String inputString){
        inputString = inputString.replaceAll("[.,!?]", "");
        String[] countWordsArray = inputString.split(" ");
        int wordCount = 0;
        for (int i = 0; i < countWordsArray.length; i++){
            if (wordCount < countWordsArray.length && !countWordsArray[i].equals("")) {
                wordCount++;
            }
        }

        return wordCount;
    }


    /*                         Longest Word Finder Method                           */
    //Create a method to find the longest word in a string
    public static String countLongWords(String inputString){
        String[] longWordCountArray = inputString.replaceAll("[,.!?]", "").split(" ");
        String longWordCount = "";
        for (int i = 0; i < longWordCountArray.length; i++){
            if (longWordCount.length() < longWordCountArray[i].length() && !longWordCountArray[i].equals("")) {
                longWordCount = longWordCountArray[i];
            }
        }
        return longWordCount;
    }




    /*                         Shortest Word Finder Method                           */
    //Create a method to find the shortest word in a string
    public static String countShortWords(String inputString){
        inputString = inputString.replaceAll("[.,!?]", "").trim();
        String[] shortWordCountArray = inputString.split(" ");
        String shortWordCount = shortWordCountArray[0];
        for (int i = 0; i < shortWordCountArray.length; i++){
            if (shortWordCount.length() > shortWordCountArray[i].length() && !shortWordCountArray[i].equals("")) {
                    shortWordCount = shortWordCountArray[i];
                }
        }
        return shortWordCount;

    }



    public static void main(String[] args) {
        System.out.print("Please enter text to analyze: ");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();


        //Test Strings
//        String inputString = "Hello! Welcome to Kenzie.  My name is Robert, and I'm here with my friend Waldo.  Have you met waldo?";
//      String inputString = "This is a string.";
//      String inputString = " Spaces         do not bother my code ";
//      String inputString = "\n";
//      String inputString = "Don't strip the middle-of-the-word symbols";
//      String inputString = "Can you handle this punctuation? Can you? Can you?!";
//      String inputString = "!!!";

        //Declare variables
        int totalA = 0;
        int totalE = 0;
        int totalI = 0;
        int totalO = 0;
        int totalU = 0;

        int totalPeriod = 0;
        int totalComma = 0;
        int totalExclamation = 0;
        int totalQuestionKMark =0;

        int numCharacters = 0;
        int numWords = 0;
        String longestWord = "";
        String shortestWord = "";



        //Call your methods here in main()
//        countCharacter();
        countLetter(inputString);
        countWords(inputString);
        countLongWords(inputString);
        countShortWords(inputString);




        System.out.println("****Text Analyzer Program***");
        //Print out your findings to the console here
        System.out.println("Total [a]: " + countCharacter(inputString, 'a'));
        System.out.println("Total [e]: " + countCharacter(inputString, 'e'));
        System.out.println("Total [i]: " + countCharacter(inputString, 'i'));
        System.out.println("Total [o]: " + countCharacter(inputString, 'o'));
        System.out.println("Total [u]: " + countCharacter(inputString, 'u'));
        System.out.println("Total periods: " + countCharacter(inputString, '.'));
        System.out.println("Total commas: " + countCharacter(inputString, ','));
        System.out.println("Total exclamation points: " + countCharacter(inputString, '!'));
        System.out.println("Total question marks: " + countCharacter(inputString, '?'));
        System.out.println("Total characters: " + countLetter(inputString));
        System.out.println("Total words: " + countWords(inputString));
        System.out.println("The longest word: " + countLongWords(inputString));
        System.out.println("The shortest word: " + countShortWords(inputString));
    }
}

