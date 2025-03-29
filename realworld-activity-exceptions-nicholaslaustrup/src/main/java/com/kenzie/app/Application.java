package com.kenzie.app;

import java.io.*;
import java.util.Scanner;


/*
 * Java Program picks a random lucky number or a fortune, but only if exceptions are handled
 */
public class Application {
    public static final String FORTUNE_FILENAME = "fortunes.txt";

    public static void main(String args[]) {
        try {
            //DO NOT MODIFY THIS BLOCK
            Scanner scan = new Scanner(System.in);
            String input;
            int numberInput = 0;
            boolean hasValidUserInput = false;
            //END BLOCK

            //Read in user input - loop until user enters correct input
            while (!hasValidUserInput) {
                try {
                    displayMenu();
                    input = scan.nextLine();
                    checkForEmptyString(input);
                    numberInput = convertStringInput(input);
                    //DO NOT MODIFY THIS BLOCK
                    //Check user input for valid number value
                    isInputValid(numberInput);
                    if (numberInput > 0) {
                        hasValidUserInput = true;
                    }
                } catch (CustomEmptyStringException a) {
                    System.out.println(a.getMessage() + " Please try again.");
                }
                catch (NumberFormatException e) {
                    System.out.println("Non-numeric string was entered. Please try again.");
                } catch (IllegalArgumentException b) {
                    System.out.println(b.getMessage() + ". Please try again.");
                }
            }


            //Selects and displays fortune based on number found
            displayResult(numberInput);
            //END BLOCK
        } catch (Exception catchAll) {
            System.out.println(catchAll);
        }
    }

    private static void displayResult(int number) throws FileNotFoundException, IOException  {
        // display result
        switch (number) {
            case 1:
                System.out.print("Your lucky number is: ");
                System.out.println(pickRandomNumber());
                break;
            case 2:
                System.out.print("Your lucky fortune is: ");
                System.out.println(pickFortune(FORTUNE_FILENAME));
                break;
            case 3:
                System.out.println("Goodbye. Try your luck another time!");
                break;
            default:
                System.out.println("Input not recognized.");
                break;
        }
    }

    // this method throws an automatic exception
    public static int convertStringInput(String input){
        return Integer.parseInt(input);
    }

    // this method will be coded to throw a standard exception
    public static boolean isInputValid(int cleanNumber) throws IllegalArgumentException {
        if (cleanNumber > 3 || cleanNumber < 1) {
            throw new IllegalArgumentException("Number must be between 1 and 3");
        } return true;
    }
    
    // Use this method to throw a custom Exception of type CustomWhiteSpaceException
    // With message: "Invalid input: Empty string entered."
    public static void checkForEmptyString(String input) throws CustomEmptyStringException {
            if (input.isEmpty()){
                throw new CustomEmptyStringException("Invalid input: Empty string entered.");
        }
    }


    public static void displayMenu() {
        System.out.println();
        System.out.println("*******Hello Fortune Hunter!*******");
        System.out.println();
        System.out.println("Enter a number between 1-3:");
        System.out.println("1. Generate your lucky number");
        System.out.println("2. Get your fortune");
        System.out.println("3. Exit program");
        System.out.println();
        System.out.print(">");

    }

    public static int pickRandomNumber() {
        int min = 1;
        int max = 10;

        // generate random int value from 50 to 100
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }

    public static String pickFortune(String filename) throws FileNotFoundException, IOException {
        int random_int = pickRandomNumber();

        // open the file
        FileInputStream fstream = new FileInputStream(filename);
        BufferedReader breader = new BufferedReader(new InputStreamReader(fstream));

        String strLine = "";

        // read File Line By Line
        for (int i = 1; i < random_int; i++) {
            strLine = breader.readLine();
        }

        // close the input stream
        fstream.close();
        return strLine;
    }
}

