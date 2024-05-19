package com.example.reciperealm;

// Plan Class Created to initialise the Plan data from FireBase
public class Plan {
    String dayChoice, timeChoice, recipeName, recipeDesc, recipeInstructions, recipeIngredients;

    //Empty constructor required for Firebase
    public Plan(){}
    //Full constructor for Plan Class
    public Plan(String dayChoice, String timeChoice, String recipeName, String recipeDesc, String recipeInstructions, String recipeIngredients) {
        this.dayChoice = dayChoice;
        this.timeChoice = timeChoice;
        this.recipeName = recipeName;
        this.recipeDesc = recipeDesc;
        this.recipeInstructions = recipeInstructions;
        this.recipeIngredients = recipeIngredients;
    }

    //Getters and Setters for Plan Class
    public String getDayChoice() {
        return dayChoice;
    }

    public void setDayChoice(String dayChoice) {
        this.dayChoice = dayChoice;
    }

    public String getTimeChoice() {
        return timeChoice;
    }

    public void setTimeChoice(String timeChoice) {
        this.timeChoice = timeChoice;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDesc() {
        return recipeDesc;
    }

    public void setRecipeDesc(String recipeDesc) {
        this.recipeDesc = recipeDesc;
    }

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}
