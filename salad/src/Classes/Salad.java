package Classes;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Salad {
    private String base;
    private ArrayList<String> toppings;
    private String dressing;

    public void setDressing(String dressing, int ounces) {
// the other side of () secret tunnel
//do NOT make static
        //objectName.whichClassVariable
        this.dressing = dressing;

    }

    //Declare addToppingsMethod here
    //option 1 for method (1 topping at a time)
    public void addTopping(String topping) {
        toppings.add(topping); //adding the one item to the end

    }

    //option 2 method adds to a dynamic list
    public void addMultipleTopping(ArrayList<String> toppingList) {
       // this.toppings = toppingList(); //completely replace the current list
        this.toppings.addAll(toppingList); //add everything from the parameter list

    }
    //Constructor = accessor word, class name
    public
    Salad(String base, ArrayList<String> toppings, String dressing) {

    }

}
