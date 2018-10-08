package com.ics499.mixme.model;

import java.util.ArrayList;

public class Catalog {

    private ArrayList<Ingredient> allIngredients = new ArrayList<>();
    private ArrayList<Ingredient> workingIngredients = new ArrayList<>();
    private ArrayList<Drink> makable = new ArrayList<>();
    private ArrayList<Drink> nearMakable = new ArrayList<>();

//make a singleton
    private static Catalog catalog;

    private Catalog() {
        this.allIngredients = new ArrayList<>();
        this.workingIngredients = new ArrayList<>();
        this.makable = new ArrayList<>();
        this.nearMakable = new ArrayList<>();
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
}
