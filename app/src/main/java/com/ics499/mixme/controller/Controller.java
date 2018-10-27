package com.ics499.mixme.controller;

import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.widget.EditText;
import android.widget.Toast;

import com.ics499.mixme.model.Catalog;
import com.ics499.mixme.model.Drink;
import com.ics499.mixme.model.Ingredient;
import com.ics499.mixme.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class Controller {

    private static Controller controller;
    private Catalog catalog;
    private User user;

    private Controller() {
        catalog = Catalog.getInstance();
        user = User.getInstance();
    }

    /**
     * Create a static method to get instance.
     */
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }


    public ArrayList<String> getIngredientList() {
        // to be replaced with catalog.getAllIngredints()
        ArrayList<String> ingredients = new ArrayList<>();

        // populate list------------------to be replaced with call to get actual list from db
//        ingredients.add("Orange Juice");
//        ingredients.add("Vodka");
//        ingredients.add("Brandy");
//        ingredients.add("Whiskey");
//        ingredients.add("Rye");
//        ingredients.add("Dry Vermouth");
//        ingredients.add("Sour");
//        ingredients.add("Coke");
//        ingredients.add("Lime Juice");
//        ingredients.add("Midori");
//        ingredients.add("Jagermeister");
//        ingredients.add("Rumpleminze");
//        ingredients.add("Yukon Jack");
//        ingredients.add("Ginger Beer");
//        ingredients.add("Scotch");
//        ingredients.add("Beer");


        //return ingredients;
        for (Ingredient i : catalog.getAllIngredients()) {
            ingredients.add(i.getName());
        }

        return ingredients;
    }

    public void searchDrinks(SparseBooleanArray sba, ArrayList<String> makableNames,
                             ArrayList<String> nearMakableNames, ArrayList<String> nearMakableMatch) {

        //Search each drink for matching ingredients
        //add to each list if matching at least 66%

        ArrayList<Ingredient> allIngredients = catalog.getAllIngredients();
        ArrayList<Integer> usingIngredientIDs = new ArrayList<>();

        // turn sparse boolean into sorted integer list of workable ingredients' ids
        for (int i = 0; i < allIngredients.size(); i++) {
            if (sba.get(i) == true) {
                usingIngredientIDs.add(i);
            }
        }

        catalog.setWorkingIngredientIDs(usingIngredientIDs);

        StringBuilder test = new StringBuilder();
        test.append("ingrdient Ids: ");
        for (Integer i : usingIngredientIDs) {
            test.append(i.toString());
            test.append("\t");
        }
        Log.d("Debug", test.toString());


        catalog.searchDrinks();

        makableNames.addAll(catalog.getMakableNames());
        nearMakableNames.addAll(catalog.getNearMakableNames());
        nearMakableMatch.addAll(catalog.getNearMakableMatch());

    }

    public void createDrink(String drinkName, ArrayList<String> ingredientNames, ArrayList<String> ingredientVolumes,
                            ArrayList<String> ingredientUnits, ArrayList<Integer> ingredientIDs,
                            String directions, String glassType, ArrayList<String> ingredientCats) {

        // pass along data to catalog, except convert sparseBooleanArray to ArrayList of Integers
        // not using sba yet
        catalog.createDrink(drinkName, ingredientNames, ingredientVolumes, ingredientUnits,
                ingredientIDs, directions, glassType, ingredientCats);

    }


    public ArrayList<String> getCreationIngredNames() {
        return catalog.getCreationIngredNames();
    }

    public ArrayList<String> getCreationVolumes() {
        return catalog.getCreationVolumes();
    }

    public ArrayList<String> getCreationUnits() {
        return catalog.getCreationUnits();
    }

    public void removeCreationIngredient(int position) {
        catalog.removeCreationIngredient(position);
    }

    public void setCreationName(String creationName) {
        catalog.setCreationName(creationName);
    }

    public void setCreationInstructions(String creationInstuctions) {
        catalog.setCreationInstructions(creationInstuctions);
    }

    public void setCreationGlassType(String creationGlassType) {
        catalog.setCreationGlassType(creationGlassType);
    }

    public String getCreationName() {
        return catalog.getCreationName();
    }

    public String getCreationInstructions() {
        return catalog.getCreationInstructions();
    }

    public String getCreationGlassType() {
        return catalog.getCreationGlassType();
    }

    public String getIngredientName(int ingredientID) {
        return catalog.getIngredientName(ingredientID);
    }

    public void setCreationIngredient(int ingredientId, double ingredientVolume, int units, String newIngredientName,
                                      String category) {
        String unitString;
        switch (units) {
            case 0:
                unitString = "Ounces";
                break;
            case 1:
                unitString = "Milliliters";
                break;
            case 2:
                unitString = "Parts";
                break;
            case 3:
                unitString = "Pieces";
                break;
            default:
                unitString = "Dashes";
                break;
        }
        Ingredient.Category cat = Ingredient.Category.getCategory(category);

        catalog.setCreationIngredient(ingredientId, ingredientVolume, unitString, newIngredientName, cat);

    }

//    public void setCreationIngredient(String newIngredientName, int categoryID, double ingredientVolume, int units) {
//        catalog.setCreationIngredient(newIngredientName, categoryID, ingredientVolume, units);
//    }

    public void addCreation() {
        catalog.addCreation();
    }
}
