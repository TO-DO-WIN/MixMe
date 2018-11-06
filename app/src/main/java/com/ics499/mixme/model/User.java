package com.ics499.mixme.model;

import android.util.SparseBooleanArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    private String userName;
    private ArrayList<Ingredient> myIngreds = new ArrayList<>();
    private ArrayList<Ingredient> shoppingLS = new ArrayList<>();
    private ArrayList<Ingredient> shoppingGS = new ArrayList<>();
    private ArrayList<Drink> faves = new ArrayList<>();
    Catalog catalog = Catalog.getInstance();

    // make a singleton
    private static User user;

    private User() {
        this.myIngreds = new ArrayList<>();
        this.shoppingLS = new ArrayList<>();
        this.shoppingGS = new ArrayList<>();
        this.faves = new ArrayList<>();
    }

    public static User getInstance(){
        if(user == null){
            user = new User();

            // some data to use til fully functioning








        }
        return user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Ingredient> getMyIngreds() {
        return myIngreds;
    }

    public void setMyIngreds(ArrayList<Ingredient> myIngreds) {
        this.myIngreds = myIngreds;
    }

    public ArrayList<Ingredient> getShoppingLS() {
        return shoppingLS;
    }

    public void setShoppingLS(ArrayList<Ingredient> shoppingLS) {
        this.shoppingLS = shoppingLS;
    }

    public ArrayList<Ingredient> getShoppingGS() {
        return shoppingGS;
    }

    public void setShoppingGS(ArrayList<Ingredient> shoppingGS) {
        this.shoppingGS = shoppingGS;
    }

    public ArrayList<Drink> getFaves() {
        return faves;
    }

    public void setFaves(ArrayList<Drink> faves) {
        this.faves = faves;
    }

    public ArrayList<String> getMyIngredientNames(){
        ArrayList<String> ingredientNames = new ArrayList<>();

        for (Ingredient i: myIngreds)
            ingredientNames.add(i.getName());

        return ingredientNames;
    }

    public ArrayList<Integer> getMyIngredientIDs(){
        ArrayList<Integer> ingredientIDs = new ArrayList<>();

        for (Ingredient i: myIngreds)
            ingredientIDs.add(i.getId());

        return ingredientIDs;
    }

    public boolean isFavorite(String drinkName) {
        for (Drink d: faves){
            if (d.getName().equals(drinkName)) return true;
        } return false;
    }

    public void addFavorite(String drinkName) {

        Drink d = catalog.getDrinkByName(drinkName);
        faves.add(d);
    }

    public void addIngredientsToCabinet(SparseBooleanArray sba){
        ArrayList<Ingredient> allIngreds = new ArrayList<>();
        allIngreds = catalog.getAllIngredients();
        for (int i = 0; i < allIngreds.size(); i++){
            if (sba.get(i)){
                myIngreds.add(allIngreds.get(i));
            }
        }
    }
}
