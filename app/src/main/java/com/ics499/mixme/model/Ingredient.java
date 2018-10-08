package com.ics499.mixme.model;

public class Ingredient {

    private String name;
    private double volume;
    private String unit;
    private int id;

    public Ingredient(String name, double volume, String unit, int id) {
        this.name = name;
        this.volume = volume;
        this.unit = unit;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
