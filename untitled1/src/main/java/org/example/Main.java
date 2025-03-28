package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

// public String spinningWord(String input){
//            String answer = "You can write this function";
            String[] stringArray = input.trim().split("\\s");
            String answers = "";


            for (int i = 0; i < stringArray.length; i++){
                if (stringArray[i].length() >= 5){
                    String reversedString = "";
                    for (int j = 0; j < stringArray[i].length(); j++){
                        char character = stringArray[i].charAt(j);
                        reversedString  = character + reversedString;
                    }
                    answers += reversedString + " ";
                } else {
                    answers += stringArray[i] + " ";
                }

            }
            System.out.println(answers.trim());
            return answers.trim();
        }

    }