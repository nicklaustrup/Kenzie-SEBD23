import Classes.Salad;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        makePuristSalad();


    }


    public static void makePuristSalad() {

        // these were made with a public accessor in the salads class
        // as an example but we chagned it to private for the next one


        //TYPE name = value;
        Salad puristSalad = new Salad();

        puristSalad.base = "spinach";

        //() is a secret tunnel to salad package
        puristSalad.setDressing("blue cheese", 2);

        puristSalad.toppings = new ArrayList<String>();

        //declare a method in the class that adds a new topping
        // to our topping list
        puristSalad.addTopping("bacon");

        //array list to add multiple toppings to call method
        ArrayList<String> additionalToppings = new ArrayList<>();
        additionalToppings.add("cheese");
        additionalToppings.add("mushroom");

        puristSalad.addMultipleTopping(additionalToppings);

        Salad kylesSalad = new Salad();
        kylesSalad.base = "arugula";
        ArrayList<String> kylesToppings = new ArrayList<>();
        System.out.println();


    }




    public static void makeTrueNeutralSalad(){
    //Base - fruit
        // Topping - none
        //  Dressing - none

        // Use CONSTRUCTORS to replace the dot accessor

        ArrayList<String> toppings = new ArrayList<>();
        toppings.add("croutons");

        Salad neutral = new Salad("lettuce", toppings, "blue cheese");





    }



//    public static void main(String[] args){
//
//    }
//    public static void main(String[] args){
//
//    }
}