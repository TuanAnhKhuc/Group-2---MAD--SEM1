package com.example.reciperealm;

// Recipe Class Created to initialise the Recipe data from FireBase
public class Recipes {
    String Cook, Description, Name, Prep, Ingredients, Instructions;
    long Serves;

    //Empty Constructor required for FireBase
    public Recipes(){

    }
    //Full Constructor for Recipe Class
    public Recipes(String cook, String description, String name, String prep, long serves, String ingredients, String instructions) {
        Cook = cook;
        Description = description;
        Name = name;
        Prep = prep;
        Serves = serves;
        Ingredients = ingredients;
        Instructions = instructions;
    }
    //Getters and Setters for Recipe Class
    public String getCook() {
        return Cook;
    }

    public void setCook(String cook) {
        Cook = cook;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrep() {
        return Prep;
    }

    public void setPrep(String prep) {
        Prep = prep;
    }

    public long getServes() {
        return Serves;
    }

    public void setServes(long serves) {
        Serves = serves;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public String getInstructions() {
        return Instructions;
    }

    public void setInstructions(String instructions) {
        Instructions = instructions;
    }
}