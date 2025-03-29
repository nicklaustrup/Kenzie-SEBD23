package com.kenzie.app;

// import necessary libraries


import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.kenzie.app.CustomHttpClient.GET_URL;
import static com.kenzie.app.CustomHttpClient.gamesJSON;

public class Main {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!

     */

    public static ArrayList<?> askedQuestions = new ArrayList();

    public static void formatQuestions(CluesDTO clues) {
        int random = new Random().nextInt(100);
        while (askedQuestions.contains(random)) {
            random = new Random().nextInt(100);
        }
        CluesDTO.Clues clue = clues.getClues().get(random);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Category: ").append(CluesDTO.Clues.getCategory().getTitle())
                .append("\n")
                .append("Question: ").append(CluesDTO.Clues.getQuestion())
                .append("\n")
                .append("Answer: ");
        System.out.println(stringBuilder);

        //TODO trying to figure out a way to save the answer for each question
        // because our game only works while the answer is static
    }

    //TODO create method for saving answer


    public static void main(String[] args) throws JsonProcessingException {
        //Write main execution code here
        Scanner scanner = new Scanner(System.in);
//        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String gameResponse = CustomHttpClient.sendGET(GET_URL);
            List<CluesDTO> game = gamesJSON(gameResponse);


            int roundNumber = 1;
            int score = 0;

            System.out.println("----------Trivia----------");
            while (roundNumber < 10) {
                System.out.println("Round " + roundNumber);
                //call Questions method here

                formatQuestions((CluesDTO) game);

                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase(CluesDTO.Clues.getAnswer())){
                    score++;
                    System.out.println("Correct!" +
                    "\nScore: " + score +
                    "\n");
                } else {
                    System.out.println("Incorrect! The answer is: " + CluesDTO.Clues.getAnswer() +
                            "\nScore: " + score +
                    "\n");

                }
                roundNumber++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        }
}

