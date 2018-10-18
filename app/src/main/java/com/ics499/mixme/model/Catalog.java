package com.ics499.mixme.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Catalog {

    private static final int MIN_PERCENT_MATCH = 66;
    private ArrayList<Ingredient> allIngredients;
    private ArrayList<Integer> workingIngredientIDs;
    private ArrayList<Drink> allDrinks;
    private ArrayList<Drink> makable;
    private ArrayList<Drink> nearMakable;

    //make a singleton
    private static Catalog catalog;

    private Catalog() {
        this.allIngredients = new ArrayList<>();
        this.workingIngredientIDs = new ArrayList<>();
        this.allDrinks = new ArrayList<>();
        this.makable = new ArrayList<>();
        this.nearMakable = new ArrayList<>();

        // Call to DB to get all ingredients and all drinks

        // mock this call for now
        // ingreds
        Ingredient a = new Ingredient("oj", 0);
        Ingredient b = new Ingredient("vodka", 1);
        Ingredient c = new Ingredient("kahlua", 2);
        Ingredient d = new Ingredient("tomato juice", 3);
        Ingredient e = new Ingredient("whiskey", 4);
        Ingredient f = new Ingredient("coke", 5);
        Ingredient g = new Ingredient("ginger beer", 6);
        Ingredient h = new Ingredient("milk", 7);

        allIngredients.add(a);
        allIngredients.add(b);
        allIngredients.add(c);
        allIngredients.add(d);
        allIngredients.add(e);
        allIngredients.add(f);
        allIngredients.add(g);
        allIngredients.add(h);

        Drink z = new Drink("srewdriver", new Ingredient[]{a, b});
        Drink y = new Drink("bloody mary", new Ingredient[]{d, b});
        Drink x = new Drink("white russian", new Ingredient[]{b, c, h});
        Drink zz = new Drink("srewdriver", new Ingredient[]{a, b});
        Drink zy = new Drink("bloody mary", new Ingredient[]{d, b});
        Drink zx = new Drink("white russian", new Ingredient[]{b, c, h});
        Drink xz = new Drink("srewdriver", new Ingredient[]{a, b});
        Drink xy = new Drink("bloody mary", new Ingredient[]{d, b});
        Drink xx = new Drink("white russian", new Ingredient[]{b, c, h});
        Drink zc = new Drink("srewdriver", new Ingredient[]{a, b});
        Drink yc = new Drink("bloody mary", new Ingredient[]{d, b});
        Drink xc = new Drink("white russian", new Ingredient[]{b, c, h});
        Drink zv = new Drink("srewdriver", new Ingredient[]{a, b});
        Drink yv = new Drink("bloody mary", new Ingredient[]{d, b});
        Drink xv = new Drink("white russian", new Ingredient[]{b, c, h});
        Drink zb = new Drink("srewdriver", new Ingredient[]{a, b});
        Drink yb = new Drink("bloody mary", new Ingredient[]{d, b});
        Drink xb = new Drink("white russian", new Ingredient[]{b, c, h});
        Drink zn = new Drink("srewdriver", new Ingredient[]{a, b});
        Drink yn = new Drink("bloody mary", new Ingredient[]{d, b});
        Drink xn = new Drink("white russian", new Ingredient[]{b, c, h});
        Drink zm = new Drink("srewdriver", new Ingredient[]{a, b});
        Drink ym = new Drink("bloody mary", new Ingredient[]{d, b});
        Drink mx = new Drink("white russian", new Ingredient[]{b, c, h});

        for (int i = 0; i < 1000; i++) {
            Drink newDrink = new Drink("newDrink", new Ingredient[]{a, b, c, d, e, f, g, h});
            allDrinks.add(newDrink);
        }

        allDrinks.add(z);
        allDrinks.add(y);
        allDrinks.add(x);
        allDrinks.add(zz);
        allDrinks.add(zy);
        allDrinks.add(zx);
        allDrinks.add(xz);
        allDrinks.add(xy);
        allDrinks.add(xx);
        allDrinks.add(zc);
        allDrinks.add(yc);
        allDrinks.add(xc);
        allDrinks.add(zv);
        allDrinks.add(yv);
        allDrinks.add(xv);
        allDrinks.add(zb);
        allDrinks.add(yb);
        allDrinks.add(xb);
        allDrinks.add(zn);
        allDrinks.add(yn);
        allDrinks.add(xn);
        allDrinks.add(zm);
        allDrinks.add(ym);
        allDrinks.add(mx);


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
            int presentIngreds = 0;
            int totalIngreds = d.getNumIngreds();
            int maxUnavailableIngreds = ((100 - MIN_PERCENT_MATCH) * totalIngreds) / 100;
            int unavailableIngreds = 0;

            Integer[] ingredIDs = d.getIngredIDs();
            // for each drink ingredient, while not exceeding maximum number of unavailable
            for (int i = 0; i < totalIngreds && unavailableIngreds <= maxUnavailableIngreds; i++) {
                // binary search for drink ingredient among ordered workingIngredients
                // if found increment presentIngreds
                // if not found increment unavailableIngreds
                if (Collections.binarySearch(workingIngredientIDs, ingredIDs[i]) < 0) {
                    unavailableIngreds++;
                } else {
                    presentIngreds++;
                }
            }
            int percent = (int) ((presentIngreds * 100.0f) / totalIngreds);

            if (percent == 100) {
                d.setPercentMatch(100);
                makable.add(d);
            } else if (percent >= 66) {
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
                            ArrayList<Integer> ingredientIDs, String directions, String glassType) {

        Ingredient[] ingredients = new Ingredient[ingredientNames.size()];
        for (int i = 0; i < ingredients.length; i++) {
            ingredients[i] = new Ingredient(ingredientNames.get(i), Double.parseDouble(ingredientVolumes.get(i)),
                    ingredientUnits.get(i), ingredientIDs.get(i));
//            ingredients[i].setName(ingredientNames.get(i));
//            ingredients[i].setVolume(Double.parseDouble(ingredientVolumes.get(i)));
//            ingredients[i].setUnit(ingredientUnits.get(i));
//            ingredients[i].setId(ingredientIDs.get(i));
        }

        Drink d = new Drink(drinkName, ingredients, directions, glassType, 0, ingredients.length);

        allDrinks.add(d);

    }
}
