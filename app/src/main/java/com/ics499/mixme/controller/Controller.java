package com.ics499.mixme.controller;

import android.content.Intent;
import android.util.SparseBooleanArray;

import com.ics499.mixme.model.Catalog;
import com.ics499.mixme.model.User;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class Controller {

    private static Controller controller;
    private Catalog catalog;
    private User user;

    private Controller(){
        Catalog.getInstance();
        User.getInstance();
    }

    /**
     * Create a static method to get instance.
     */
    public static Controller getInstance(){
        if(controller == null){
            controller = new Controller();
        }
        return controller;
    }


    public ArrayList<String> getIngredientList(){

        ArrayList<String> ingredients = new ArrayList<>();

        // populate list------------------to be replaced with call to get actual list from db
        ingredients.add("Orange Juice");
        ingredients.add("Vodka");
        ingredients.add("Brandy");
        ingredients.add("Whiskey");
        ingredients.add("Rye");
        ingredients.add("Dry Vermouth");
        ingredients.add("Sour");
        ingredients.add("Coke");
        ingredients.add("Lime Juice");
        ingredients.add("Midori");
        ingredients.add("Jagermeister");
        ingredients.add("Rumpleminze");
        ingredients.add("Yukon Jack");
        ingredients.add("Ginger Beer");
        ingredients.add("Scotch");
        ingredients.add("Beer");



        return ingredients;
    }

    public void searchDrinks(SparseBooleanArray sba,
                             ArrayList<String> drinkNames, ArrayList<String> percentMatch){

            //Search each drink for matching ingredients
            //add to each list if matching at least 66%




    }
}
