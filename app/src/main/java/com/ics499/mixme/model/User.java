package com.ics499.mixme.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    private String userName;
    private ArrayList<Ingredient> myIngreds = new ArrayList<>();
    private ArrayList<Ingredient> shoppingLS = new ArrayList<>();
    private ArrayList<Ingredient> shoppingGS = new ArrayList<>();
    private ArrayList<Drink> faves = new ArrayList<>();

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
}