package com.example.reciperealm;

import java.util.Map;

public class Recipes {
    String Cook, Description, Name, Prep, Ingredients, Instructions;
    long Serves;

    public Recipes(){

    }

    public Recipes(String cook, String description, String name, String prep, long serves, String ingredients, String instructions) {
        Cook = cook;
        Description = description;
        Name = name;
        Prep = prep;
        Serves = serves;
        Ingredients = ingredients;
        Instructions = instructions;
    }

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