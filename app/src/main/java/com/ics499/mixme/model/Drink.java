package com.ics499.mixme.model;

public class Drink {

    private static final int MAX_INGREDS = 15;
    private String name;
    private Ingredient[] ingreds = new Ingredient[MAX_INGREDS];
    private String directions;
    private String glassType;
    private int percentMatch;
    private int numIngreds;

    public Drink(String name, Ingredient[] ingreds, String directions, String glassType, int percentMatch, int numIngreds) {
        this.name = name;
        this.ingreds = ingreds;
        this.directions = directions;
        this.glassType = glassType;
        this.percentMatch = percentMatch;
        this.numIngreds = numIngreds;
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

    public Ingredient[] getIngreds() {
        return ingreds;
    }

    public void setIngreds(Ingredient[] ingreds) {
        this.ingreds = ingreds;
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

    public Integer[] getIngredIDs() {
        Integer[] ingredIDs = new Integer[MAX_INGREDS];

        for (int i = 0; i < MAX_INGREDS && (ingreds[i] != null); i++)
            ingredIDs[i] = ingreds[i].getId();

        return ingredIDs;
    }
}
