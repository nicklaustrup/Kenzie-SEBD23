package com.kenzie.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    /*********************************/
    /* Using Dynamic Arrays          */
    /*********************************/

    // Complete the Following Exercises
    // If a method returns null, "", or 0,
    // replace that return value
    // with the return value indicated by the instructions

    // Exercise One -------------------------------------------------------------------
    public static ArrayList<String> createFoodsList(String food1, String food2, String food3, String food4) {
        // Create a new ArrayList instance
        // Add each of the foods to the list
        // Then return it
        ArrayList<String> foodList = new ArrayList<>();
        foodList.add(food1);
        foodList.add(food2);
        foodList.add(food3);
        foodList.add(food4);


        return foodList;
    }

    // Exercise Two -------------------------------------------------------------------
    public static int findTheSizeOfArrayList(ArrayList<String> favoriteFood){
        // Find the size of favoriteFood using the pre-defined method in Java
        // Return the size

        return favoriteFood.size();
    }

    // Exercise Three -------------------------------------------------------------------
    // We can stack input parameters like this to save space
    public static String accessTheElementAtParticularIndex(ArrayList<String> favoriteFood, int index){
        // Access the element at a particular index using the pre-defined method in Java
        // Return the element


        return favoriteFood.get(index);
    }

    // Exercise Four -------------------------------------------------------------------
    public static int countNumOfCakes(ArrayList<String> favoriteFood) {
        // Iterate through favoriteFood and count the number of times you find the String "Cake"
        // Return the number of occurrences

        int cakeCount = 0;

        for (String cakeNum: favoriteFood) {
            if (cakeNum.equals("Cake")){
                cakeCount++;
            }

        }

        return cakeCount;
    }

    // Exercise Five -------------------------------------------------------------------
    // We can stack input parameters like this to save space
    public static void modifyTheElementAtParticularIndex(
            ArrayList<String> favoriteFood,
            int index,
            String replacementValue
    ){
        // Replace the element at index with the replacementValue
        favoriteFood.set(index, replacementValue);

    }

    // Exercise Six -------------------------------------------------------------------
    public static void sortTheArrayList(ArrayList<String> favoriteFood){
        // Sort favoriteFood alphabetically
        // The favoriteFood ArrayList can be changed by reference from the parameter
        // No need to return favoriteFood
        // Hint: Don't use the ArrayList sort method, there is an easier one inside Collections
        // Google around to find it.
        favoriteFood.sort(String::compareToIgnoreCase);
    }

    // Exercise Seven -------------------------------------------------------------------
    public static String[] convertArrayListToStringArray(ArrayList<String> favoriteFood){
        // Convert the ArrayList to a String[] array
        // Return the new String[] array
        String[] primFaveFoods = favoriteFood.toArray(new String[favoriteFood.size()]);
        return primFaveFoods;
    }

    // Exercise Eight -------------------------------------------------------------------
    public static ArrayList<String> convertStringArrayToArrayList(String[] inputStrings){
        // Convert inputStrings to an ArrayList
        // Return the ArrayList
        ArrayList<String> inputStrings2 = new ArrayList<String>(Arrays.asList(inputStrings));
        return inputStrings2;
    }
    
    public static void main(String[] args) {
        // You do not need to modify anything in this method.
        // Just write your code above, then verify your answers in the console output.

        System.out.println("Exercise One");
        ArrayList<String> favoriteFood = createFoodsList("Pizza", "Pie", "Milkshakes", "Cake");
        System.out.println(favoriteFood);
        // This should print: [Pizza, Pie, Milkshakes, Cake]

        // ----------------------------------------------------------------------------------------------
        System.out.println("Exercise Two");
        int size = findTheSizeOfArrayList(favoriteFood);
        System.out.println(size);
        // This should print: 4

        // ----------------------------------------------------------------------------------------------
        System.out.println("Exercise Three");
        favoriteFood = new ArrayList<>();
        favoriteFood.add("Waffles");
        favoriteFood.add("Bananannannnanas");
        favoriteFood.add("Squid");
        favoriteFood.add("Pizza");
        favoriteFood.add("Chicken nuggets made of tofu");
        String element = accessTheElementAtParticularIndex(favoriteFood, 3);
        System.out.println(element);
        // This should print: "Pizza"

        // ----------------------------------------------------------------------------------------------
        System.out.println("Exercise Four");
        ArrayList<String> cakeList = new ArrayList<>();
        cakeList.add("Waffles");
        cakeList.add("Cake");
        cakeList.add("Bananannannnanas");
        cakeList.add("Cake");
        cakeList.add("Squid pizza");
        cakeList.add("Cake");
        cakeList.add("Chicken nuggets made of tofu");
        cakeList.add("Cake");
        int numCakes = countNumOfCakes(cakeList);
        System.out.println(numCakes);
        // This should print 4

        // ----------------------------------------------------------------------------------------------
        System.out.println("Exercise Five");
        modifyTheElementAtParticularIndex(cakeList, 3, "Sushi");
        System.out.println(cakeList.get(3));
        // This should print: "Sushi"

        // ----------------------------------------------------------------------------------------------
        System.out.println("Exercise Six");
        ArrayList<String> sortableList = new ArrayList<>();
        sortableList.add("Zucchini");
        sortableList.add("Broccoli");
        sortableList.add("Apple");
        sortableList.add("Pear");
        sortTheArrayList(sortableList);
        System.out.println(sortableList);
        // This should print: [Apple, Broccoli, Pear, Zucchini]

        // ----------------------------------------------------------------------------------------------
        System.out.println("Exercise Seven");
        favoriteFood = new ArrayList<>();
        favoriteFood.add("Waffles");
        favoriteFood.add("Bananas");
        favoriteFood.add("Squid");
        favoriteFood.add("Pizza");
        favoriteFood.add("Chicken nuggets made of tofu");
        String[] stringArray = convertArrayListToStringArray(favoriteFood);
        System.out.println(Arrays.toString(stringArray));
        // This should print: [Waffles, Bananas, Squid, Pizza, Chicken nuggets made of tofu]

        // ----------------------------------------------------------------------------------------------
        System.out.println("Exercise Eight");
        String[] inputStrings = {"Waffles", "Bananas", "Oatmeal"};
        ArrayList<String> result = convertStringArrayToArrayList(inputStrings);
        System.out.println(result);
        // This should print: [Waffles, Bananas, Oatmeal]
    }
}
