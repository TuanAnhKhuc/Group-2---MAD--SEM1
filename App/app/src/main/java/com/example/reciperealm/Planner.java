package com.example.reciperealm;

// Planner.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Planner extends AppCompatActivity {
    private LinearLayout layoutWeekdays, layoutRecipes;
    private Button buttonFavRecipes;
    private Button buttonSearchRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        // Initialize buttons and layouts
        Button buttonMorning = findViewById(R.id.buttonMorning);
        Button buttonNoon = findViewById(R.id.buttonNoon);
        Button buttonNight = findViewById(R.id.buttonNight);
        layoutWeekdays = findViewById(R.id.layoutWeekdays);
        layoutRecipes = findViewById(R.id.layoutRecipes);

        // Initialize the Fav Recipes button
        buttonFavRecipes = findViewById(R.id.buttonFavRecipes);

        //Initialize the Search for recipes button
        buttonSearchRecipes = findViewById(R.id.buttonSearchRecipes);



        // Event listeners for morning/noon/night
        buttonMorning.setOnClickListener(view -> showWeekdays());
        buttonNoon.setOnClickListener(view -> showWeekdays());
        buttonNight.setOnClickListener(view -> showWeekdays());

        // Add event listeners to weekday buttons (e.g., Monday)
        Button buttonSunday = findViewById(R.id.buttonSunday);
        buttonSunday.setOnClickListener(view -> showRecipeSelection());

        Button buttonMonday = findViewById(R.id.buttonMonday);
        buttonMonday.setOnClickListener(view -> showRecipeSelection());

        Button buttonTuesday = findViewById(R.id.buttonTuesday);
        buttonTuesday.setOnClickListener(view -> showRecipeSelection());

        Button buttonWednesday = findViewById(R.id.buttonWednesday);
        buttonWednesday.setOnClickListener(view -> showRecipeSelection());

        Button buttonThursday = findViewById(R.id.buttonThursday);
        buttonThursday.setOnClickListener(view -> showRecipeSelection());

        Button buttonFriday = findViewById(R.id.buttonFriday);
        buttonFriday.setOnClickListener(view -> showRecipeSelection());

        Button buttonSaturday = findViewById(R.id.buttonSaturday);
        buttonSaturday.setOnClickListener(view -> showRecipeSelection());

        // Set up the click listener for the Fav Recipes button
        buttonFavRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFavoriteRecipes();
            }
        });

        // Set up the click listener for the Search for recipes button
        buttonSearchRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRecipeListIntent = new Intent(Planner.this, RecipeList.class);
                startActivity(goToRecipeListIntent);
            }
        });

    }

    // Show weekdays layout
    private void showWeekdays() {
        layoutWeekdays.setVisibility(View.VISIBLE);
        layoutRecipes.setVisibility(View.GONE);
    }

    // Show recipe selection layout
    private void showRecipeSelection() {
        layoutRecipes.setVisibility(View.VISIBLE);
        layoutWeekdays.setVisibility(View.GONE);
    }

    //Show favourite recipe section
    private void openFavoriteRecipes() {
        // Create an Intent to start the FavouriteRecipes activity
        Intent intent = new Intent(Planner.this, FavoriteRecipes.class);
        startActivity(intent);
    }

    //Show recipe list section
    private void openRecipeList() {
        // Create an Intent to start the Recipes activity
        Intent intent = new Intent(Planner.this, RecipeList.class);
        startActivity(intent);
    }
}
