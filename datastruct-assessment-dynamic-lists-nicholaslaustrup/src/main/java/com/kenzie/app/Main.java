package com.kenzie.app;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Main {

    public static void main(String[] args) {
        // Step 1 - Print out a welcome message and instructions
        // Step 2 - In a loop, collect each attendee name and store it in a list
        // Step 3 - when they enter a blank name, then stop looping
        // Step 4 - Then iterate over the attendee list to create the output string (using StringBuilder!)
        //
        // Note that there are three distinct formats for the output string depending on the number of attendees
        // More than 2 attendees:
        // You have invited: Leslie Knope, April Ludgate, and Ron Swanson
        //
        // Exactly 2 attendees:
        // You have invited: Leslie Knope and Ron Swanson
        // 
        // Only 1 attendee:
        // You have invited: Leslie Knope
        // 
        // Your output should exactly match the way these are formatted with spaces, commas, and the "and"

        // Your Code Here



        System.out.println("Welcome to the show! Please enter the names of all attendees in your party:");
        System.out.println("(When done press the Enter key)");

        //Creating attendees variable

        //Input Scanner
        Scanner attendees = new Scanner(System.in);


        //Creating the Array List
        ArrayList<String> attendeesList = new ArrayList<>();

        attendeesList.add(attendees.nextLine());

// took heavy inspiration from classmate nicolas s on inscribe to figure this loop out
        // loop for adding attendees to arraylist
        while (attendeesList.get(attendeesList.size() -1 ) != "") {
            attendeesList.add(attendees.nextLine());
            }

        //removing blank input
        attendeesList.remove(attendeesList.size() - 1);

        //Ending Scanner
        attendees.close();


        System.out.println("Thank you!");
        System.out.println("You have invited: " + formatAttendeeList(attendeesList));



    }


    public static String formatAttendeeList(ArrayList<String> attendeesList) {
        StringBuilder attendeeBuilder = new StringBuilder();

        attendeeBuilder.append(attendeesList);

        if (attendeesList.size() >= 2) {
            int comma = attendeeBuilder.lastIndexOf(",") +1;
            attendeeBuilder.insert(comma, " and");
            if (attendeesList.size() == 2){
                int comma2 = attendeeBuilder.indexOf(",");
                attendeeBuilder.delete(comma2, comma2 +1);
            }

        }
       String scrubbedList = attendeeBuilder.substring(1, attendeeBuilder.length() -1);



    return scrubbedList;
    }
}
