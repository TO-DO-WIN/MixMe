package com.ics499.mixme.controller;

import java.util.ArrayList;

public class Controller {

    public Controller(){}

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
}
