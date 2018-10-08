package com.ics499.mixme.model;

import java.util.ArrayList;

public class Catalog {

    private static final int MIN_PERCENT_MATCH = 66;
    private ArrayList<Ingredient> allIngredients;
    private ArrayList<Ingredient> workingIngredients;
    private ArrayList<Drink> allDrinks;
    private ArrayList<Drink> makable;
    private ArrayList<Drink> nearMakable;

//make a singleton
    private static Catalog catalog;

    private Catalog() {
        this.allIngredients = new ArrayList<>();
        this.workingIngredients = new ArrayList<>();
        this.allDrinks = new ArrayList<>();
        this.makable = new ArrayList<>();
        this.nearMakable = new ArrayList<>();

        // Call to DB to get all ingredients and all drinks
    }

    public static Catalog getInstance(){
        if(catalog == null){
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

    public ArrayList<Ingredient> getWorkingIngredients() {
        return workingIngredients;
    }

    public void setWorkingIngredients(ArrayList<Ingredient> workingIngredients) {
        this.workingIngredients = workingIngredients;
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

    public void searchDrinks(ArrayList<String> drinkNames, ArrayList<String> percentMatch) {

        for(Drink d: allDrinks){
            int presentIngreds = 0;
            int totalIngreds = d.getNumIngreds();
            int maxUnavailableIngreds = ((100 - MIN_PERCENT_MATCH) * totalIngreds) / 100;
            int unavailableIngreds = 0;

            Ingredient[] ingreds = d.getIngreds();
            // for each drink ingredient, while not exceeding maximum number of unavailable
            for(int i = 0; i < totalIngreds && unavailableIngreds <= maxUnavailableIngreds; i++){
                // binary search for drink ingredient among ordered workingIngredients
                // if found increment presentIngreds
                // if not found increment unavailableIngreds
            }
            int percent = (int)((presentIngreds * 100.0f) / totalIngreds);

            if (percent == 100) {
                d.setPercentMatch(100);
                makable.add(d);
            }
            else if (percent > 66){
                d.setPercentMatch(percent);
                nearMakable.add(d);
            }
        }

    }
}