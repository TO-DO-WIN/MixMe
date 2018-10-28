package com.ics499.mixme.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Catalog {

    private static final int MIN_PERCENT_MATCH = 51;
    private ArrayList<Ingredient> allIngredients;
    private ArrayList<Integer> workingIngredientIDs;
    private ArrayList<Drink> allDrinks;
    private ArrayList<Drink> makable;
    private ArrayList<Drink> nearMakable;
    private Drink creation;
    private ArrayList<Ingredient> newIngredients;
    private static final int NO_ID = -1;

    //make a singleton
    private static Catalog catalog;

    private Catalog() {
        this.allIngredients = new ArrayList<>();
        this.workingIngredientIDs = new ArrayList<>();
        this.allDrinks = new ArrayList<>();
        this.makable = new ArrayList<>();
        this.nearMakable = new ArrayList<>();
        this.creation = new Drink();
        this.newIngredients = new ArrayList<>();

        // Call to DB to get all ingredients and all drinks

        // mock this call for now
        // ingreds
        Ingredient a = new Ingredient("oj", 3, "Ounces", 0, Ingredient.Category.MIXER);
        Ingredient b = new Ingredient("vodka", 2, "Ounces", 1, Ingredient.Category.SPIRIT);
        Ingredient c = new Ingredient("kahlua", 2, Ingredient.Category.LIQUEUR);
        Ingredient d = new Ingredient("tomato juice", 3, Ingredient.Category.MIXER);
        Ingredient e = new Ingredient("whiskey", 4, Ingredient.Category.SPIRIT);
        Ingredient f = new Ingredient("coke", 5, Ingredient.Category.MIXER);
        Ingredient g = new Ingredient("ginger beer", 6, Ingredient.Category.MIXER);
        Ingredient h = new Ingredient("milk", 7, Ingredient.Category.MIXER);
        Ingredient i = new Ingredient("lime wedge", 8, Ingredient.Category.GARNISH);
        Ingredient j = new Ingredient("light rum", 9, Ingredient.Category.SPIRIT);
        Ingredient k = new Ingredient("dark rum", 10, Ingredient.Category.SPIRIT);
        Ingredient l = new Ingredient("passion fruite juice", 11, Ingredient.Category.MIXER);
        Ingredient m = new Ingredient("pinapple juice", 12, Ingredient.Category.MIXER);


        allIngredients.add(a);
        allIngredients.add(b);
        allIngredients.add(c);
        allIngredients.add(d);
        allIngredients.add(e);
        allIngredients.add(f);
        allIngredients.add(g);
        allIngredients.add(h);
        allIngredients.add(i);
        allIngredients.add(j);
        allIngredients.add(k);
        allIngredients.add(l);
        allIngredients.add(m);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(a);
        ingredients.add(b);

        Drink z = new Drink("srewdriver", ingredients, "", "", 0);
//        Drink y = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink x = new Drink("white russian", new Ingredient[]{b, c, h});
//        Drink zz = new Drink("moscow mule", new Ingredient[]{b, g, i});
//        Drink zy = new Drink("whiskey coke", new Ingredient[]{e, f});
//        Drink zx = new Drink("chinh's special", new Ingredient[]{j, k, l, m});
//        Drink xz = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink xy = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink xx = new Drink("white russian", new Ingredient[]{b, c, h});
//        Drink zc = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink yc = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink xc = new Drink("white russian", new Ingredient[]{b, c, h});
//        Drink zv = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink yv = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink xv = new Drink("white russian", new Ingredient[]{b, c, h});
//        Drink zb = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink yb = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink xb = new Drink("white russian", new Ingredient[]{b, c, h});
//        Drink zn = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink yn = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink xn = new Drink("white russian", new Ingredient[]{b, c, h});
//        Drink zm = new Drink("srewdriver", new Ingredient[]{a, b});
//        Drink ym = new Drink("bloody mary", new Ingredient[]{d, b});
//        Drink mx = new Drink("white russian", new Ingredient[]{b, c, h});

        creation = z;
//        for (int i = 0; i < 1000; i++) {
//            Drink newDrink = new Drink("newDrink", new Ingredient[]{a, b, c, d, e, f, g, h});
//            allDrinks.add(newDrink);
//        }

//        allDrinks.add(z);
//        allDrinks.add(y);
//        allDrinks.add(x);
//        allDrinks.add(zz);
//        allDrinks.add(zy);
//        allDrinks.add(zx);
//        allDrinks.add(xz);
//        allDrinks.add(xy);
//        allDrinks.add(xx);
//        allDrinks.add(zc);
//        allDrinks.add(yc);
//        allDrinks.add(xc);
//        allDrinks.add(zv);
//        allDrinks.add(yv);
//        allDrinks.add(xv);
//        allDrinks.add(zb);
//        allDrinks.add(yb);
//        allDrinks.add(xb);
//        allDrinks.add(zn);
//        allDrinks.add(yn);
//        allDrinks.add(xn);
//        allDrinks.add(zm);
//        allDrinks.add(ym);
//        allDrinks.add(mx);


    }

    public static Catalog getInstance() {
        if (catalog == null) {
            catalog = new Catalog();
        }
        return catalog;
    }

    public ArrayList<Ingredient> getAllIngredients() {
        return allIngredients;
    }

    public void setAllIngredients(ArrayList<Ingredient> allIngredients) {
        this.allIngredients = allIngredients;
    }

    public ArrayList<Integer> getWorkingIngredientIDs() {
        return workingIngredientIDs;
    }

    public void setWorkingIngredientIDs(ArrayList<Integer> workingIngredients) {
        this.workingIngredientIDs = workingIngredients;
    }

    public ArrayList<Drink> getAllDrinks() {
        return allDrinks;
    }

    public void setAllDrinks(ArrayList<Drink> allDrinks) {
        this.allDrinks = allDrinks;
    }

    public ArrayList<Drink> getMakable() {
        return makable;
    }

    public void setMakable(ArrayList<Drink> makable) {
        this.makable = makable;
    }

    public ArrayList<Drink> getNearMakable() {
        return nearMakable;
    }

    public void setNearMakable(ArrayList<Drink> nearMakable) {
        this.nearMakable = nearMakable;
    }

    public void searchDrinks() {

        makable.clear();
        nearMakable.clear();

        for (Drink d : allDrinks) {
            int currentWeight = 0;
            int totalIngreds = d.getNumIngreds();
            int totalDrinkWeight = d.getTotalWeight();
            int maxUnavailableWeight = ((100 - MIN_PERCENT_MATCH) * totalDrinkWeight) / 100;
            int unavailableWeight = 0;

            ArrayList<Integer> ingredIDs = d.getIngredIDs();
            // for each drink ingredient, while not exceeding maximum number of unavailable
            for (int i = 0; i < totalIngreds && unavailableWeight <= maxUnavailableWeight; i++) {
                // binary search for drink ingredient among ordered workingIngredients
                // if found increment presentIngreds
                // if not found increment unavailableIngreds
                if (Collections.binarySearch(workingIngredientIDs, ingredIDs.get(i)) < 0) {
                    unavailableWeight += allIngredients.get(ingredIDs.get(i)).getWeight();
                } else {
                    currentWeight += allIngredients.get(ingredIDs.get(i)).getWeight();
                }
            }
            int percent = (int) ((currentWeight * 100.0f) / totalDrinkWeight);

            if (percent == 100) {
                d.setPercentMatch(100);
                makable.add(d);
            } else if (percent >= MIN_PERCENT_MATCH) {
                d.setPercentMatch(percent);
                nearMakable.add(d);
            }
        }

    }

    public ArrayList<String> getMakableNames() {
        ArrayList<String> makableNames = new ArrayList<>();

        for (Drink d : makable) {
            makableNames.add(d.getName());
        }
        return makableNames;
    }

    public ArrayList<String> getNearMakableNames() {
        ArrayList<String> nearMakableNames = new ArrayList<>();

        for (Drink d : nearMakable) {
            nearMakableNames.add(d.getName());
        }
        return nearMakableNames;
    }

    public ArrayList<String> getNearMakableMatch() {
        ArrayList<String> nearMakableMatch = new ArrayList<>();

        for (Drink d : nearMakable) {
            nearMakableMatch.add(Integer.toString(d.getPercentMatch()));
        }
        return nearMakableMatch;
    }

    public void createDrink(String drinkName, ArrayList<String> ingredientNames,
                            ArrayList<String> ingredientVolumes, ArrayList<String> ingredientUnits,
                            ArrayList<Integer> ingredientIDs, String directions, String glassType,
                            ArrayList<String> ingredientCats) {

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientNames.size(); i++) {

            // set category from type String to type Category
            Ingredient.Category cat;
            switch (ingredientCats.get(i)) {
                case "GARNISH":
                    cat = Ingredient.Category.GARNISH;
                    break;
                case "MIXER":
                    cat = Ingredient.Category.MIXER;
                    break;
                case "LIQUEUR":
                    cat = Ingredient.Category.LIQUEUR;
                    break;
                case "SPIRIT":
                    cat = Ingredient.Category.SPIRIT;
                    break;

                // should handle for null which should never happen
                default:
                    cat = null;
            }


            ingredients.add(new Ingredient(ingredientNames.get(i), Double.parseDouble(ingredientVolumes.get(i)),
                    ingredientUnits.get(i), ingredientIDs.get(i), cat));
//            ingredients[i].setName(ingredientNames.get(i));
//            ingredients[i].setVolume(Double.parseDouble(ingredientVolumes.get(i)));
//            ingredients[i].setUnit(ingredientUnits.get(i));
//            ingredients[i].setId(ingredientIDs.get(i));
        }

        Drink d = new Drink(drinkName, ingredients, directions, glassType, 0);

        allDrinks.add(d);

    }

    private ArrayList<Ingredient> creationIngreds = new ArrayList<>();

    public ArrayList<String> getCreationIngredNames() {
        ArrayList<String> creationIngredNames = new ArrayList<>();

        ArrayList<Ingredient> ingreds = creation.getIngreds();
        for (int i = 0; i < ingreds.size(); i++)
            creationIngredNames.add(ingreds.get(i).getName());

        return creationIngredNames;
    }

    public ArrayList<String> getCreationVolumes() {
        ArrayList<String> creationVolumes = new ArrayList<>();

        ArrayList<Ingredient> ingreds = creation.getIngreds();
        for (int i = 0; i < ingreds.size(); i++) {
            creationVolumes.add(Double.toString(ingreds.get(i).getVolume()));
        }

        return creationVolumes;
    }

    public ArrayList<String> getCreationUnits() {
        ArrayList<String> creationUnits = new ArrayList<>();

        ArrayList<Ingredient> ingreds = creation.getIngreds();
        for (int i = 0; i < ingreds.size(); i++) {
            creationUnits.add(ingreds.get(i).getUnit());
        }

        return creationUnits;
    }

    public void removeCreationIngredient(int pos) {
        creation.removeIngredient(pos);
    }

    public void setCreationName(String creationName) {
        creation.setName(creationName);
    }

    public void setCreationInstructions(String creationInstuctions) {
        creation.setDirections(creationInstuctions);
    }

    public void setCreationGlassType(String creationGlassType) {
        creation.setGlassType(creationGlassType);
    }

    public String getCreationName() {
        return creation.getName();
    }

    public String getCreationInstructions() {
        return creation.getDirections();
    }

    public String getCreationGlassType() {
        return creation.getGlassType();
    }

    public void setCreationIngredient(int ingredientId, double ingredientVolume, String units,
                                      String name, Ingredient.Category category) {
        Ingredient i;

        if (ingredientId == NO_ID){
            i = new Ingredient(name, ingredientVolume, units, NO_ID, category);
            newIngredients.add(i);
        } else {
            i = allIngredients.get(ingredientId);
            i.setVolume(ingredientVolume);
            i.setUnit(units);
        }

        creation.addIngredient(i);
    }

    public String getIngredientName(int ingredientID) {
        return allIngredients.get(ingredientID).getName();
    }
/*
    This version of setCreationIngredient method is for newly created ingredients. A newly created ingredient
    will need to have a name, not just id (doesn't have an id yet), also it needs a category. Here, the new
    ingredient can be stored in db and given and id. Ingredient list will need to be repopulated with new
    ingredient inserted in the list.
 */

    public void addCreation(){
        allDrinks.add(creation);

        int prevIngredientsSize = allIngredients.size();

        allIngredients.addAll(newIngredients);
        // need to assign ids to new ingredients
        for (int i = prevIngredientsSize; i < allIngredients.size(); i++){
            allIngredients.get(i).setId(i);
        }

        creation = new Drink();
    }

}
