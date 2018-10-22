package com.ics499.mixme.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Drink {

    private static final int MAX_INGREDS = 15;
    private String name;
    private ArrayList<Ingredient> ingreds = new ArrayList<>();
    //private Ingredient[] ingreds = new Ingredient[MAX_INGREDS];
    private String directions;
    private String glassType;
    private int percentMatch;
    private int numIngreds;
    private int totalWeight = 0;

    public int getTotalWeight() {
        return totalWeight;
    }

    private void setTotalWeight() {
        for (Ingredient i : ingreds) {
            totalWeight += i.getWeight();
        }
    }

    public Drink() {
    }

    public Drink(String name, ArrayList<Ingredient> ingreds) {
        this.name = name;
        this.ingreds = ingreds;
        //this.numIngreds = ingreds.length;
        setTotalWeight();
    }

    public Drink(String name, ArrayList<Ingredient> ingreds, String directions, String glassType, int percentMatch) {
        this.name = name;
        this.ingreds = ingreds;
        this.directions = directions;
        this.glassType = glassType;
        this.percentMatch = percentMatch;
       // this.numIngreds = numIngreds;
    }

    public static int getMaxIngreds() {
        return MAX_INGREDS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngreds() {
        return ingreds;
    }

    public void setIngreds(ArrayList<Ingredient> ingreds) {
        this.ingreds = ingreds;
    }

    public void removeIngredient(int index) {
        ingreds.remove(index);
    }


    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getGlassType() {
        return glassType;
    }

    public void setGlassType(String glassType) {
        this.glassType = glassType;
    }

    public int getPercentMatch() {
        return percentMatch;
    }

    public void setPercentMatch(int percentMatch) {
        this.percentMatch = percentMatch;
    }

    public int getNumIngreds() {
        return numIngreds;
    }

    public void setNumIngreds(int numIngreds) {
        this.numIngreds = numIngreds;
    }

    public ArrayList<Integer> getIngredIDs() {
        ArrayList<Integer> ingredIDs = new ArrayList<>();

        for (int i = 0; i < ingreds.size(); i++)
            ingredIDs.add(ingreds.get(i).getId());

        return ingredIDs;
    }
}
