package com.example.reciperealm;

// Planner.java
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Planner extends AppCompatActivity {
    private LinearLayout layoutWeekdays, layoutRecipes;
    private Button buttonAddPlanner, buttonSearchRecipes, buttonMorning, buttonNoon, buttonNight;
    private Button buttonSunday, buttonMonday, buttonTuesday, buttonWednesday, buttonThursday, buttonFriday, buttonSaturday;
    private TextView timeChoice, dayChoice, recipeChoice;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        // Initialize buttons and TextView
        buttonMorning = findViewById(R.id.buttonMorning);
        buttonNoon = findViewById(R.id.buttonNoon);
        buttonNight = findViewById(R.id.buttonNight);
        buttonSearchRecipes = findViewById(R.id.buttonSearchRecipes);
        buttonAddPlanner = findViewById(R.id.addPlannerbtn);
        timeChoice = findViewById(R.id.layoutTimeChoice);
        dayChoice = findViewById(R.id.layoutDayChoice);
        recipeChoice = findViewById(R.id.layoutRecipeChoice);

        // Initialize the weekday buttons (e.g., Monday)
        buttonSunday = findViewById(R.id.buttonSunday);
        buttonMonday = findViewById(R.id.buttonMonday);
        buttonTuesday = findViewById(R.id.buttonTuesday);
        buttonWednesday = findViewById(R.id.buttonWednesday);
        buttonThursday = findViewById(R.id.buttonThursday);
        buttonFriday = findViewById(R.id.buttonFriday);
        buttonSaturday = findViewById(R.id.buttonSaturday);

        if(getIntent().getExtras() != null){
            Bundle extras = getIntent().getExtras();

            loadPlanner();

            recipeChoice.setText(extras.getString("recipeName"));
        }

        // Set up the click listener for the Search for recipes button
        buttonSearchRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePlanner();
                openRecipeList(extras);
            }
        });

        //Set choice for time of day.
        buttonMorning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeChoice("Morning");
            }
        });
        buttonNoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeChoice("Noon");
            }
        });
        buttonNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeChoice("Night");
            }
        });

        //Set choice for day.
        buttonSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayChoice("Sunday");
            }
        });
        buttonMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayChoice("Monday");
            }
        });
        buttonTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayChoice("Tuesday");
            }
        });
        buttonWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayChoice("Wednesday");
            }
        });
        buttonThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayChoice("Thursday");
            }
        });
        buttonFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayChoice("Friday");
            }
        });
        buttonSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayChoice("Saturday");
            }
        });


    }

    private void setTimeChoice(String time){
        buttonMorning.setBackgroundColor(Color.LTGRAY);
        buttonNoon.setBackgroundColor(Color.LTGRAY);
        buttonNight.setBackgroundColor(Color.LTGRAY);
        if(time.equals("Morning")){
            buttonMorning.setBackgroundColor(Color.BLUE);
        }
        if(time.equals("Noon")){
            buttonNoon.setBackgroundColor(Color.BLUE);
        }
        if(time.equals("Night")){
            buttonNight.setBackgroundColor(Color.BLUE);
        }
        timeChoice.setText(time);
        timeChoice.setTypeface(Typeface.DEFAULT);
    }

    private void setDayChoice(String day){
        buttonSunday.setBackgroundColor(Color.LTGRAY);
        buttonMonday.setBackgroundColor(Color.LTGRAY);
        buttonTuesday.setBackgroundColor(Color.LTGRAY);
        buttonWednesday.setBackgroundColor(Color.LTGRAY);
        buttonThursday.setBackgroundColor(Color.LTGRAY);
        buttonFriday.setBackgroundColor(Color.LTGRAY);
        buttonSaturday.setBackgroundColor(Color.LTGRAY);
        if(day.equals("Sunday")){
            buttonSunday.setBackgroundColor(Color.BLUE);
        }
        if(day.equals("Monday")){
            buttonMonday.setBackgroundColor(Color.BLUE);
        }
        if(day.equals("Tuesday")){
            buttonTuesday.setBackgroundColor(Color.BLUE);
        }
        if(day.equals("Wednesday")){
            buttonWednesday.setBackgroundColor(Color.BLUE);
        }
        if(day.equals("Thursday")){
            buttonThursday.setBackgroundColor(Color.BLUE);
        }
        if(day.equals("Friday")){
            buttonFriday.setBackgroundColor(Color.BLUE);
        }
        if(day.equals("Saturday")){
            buttonSaturday.setBackgroundColor(Color.BLUE);
        }
        dayChoice.setText(day);
        dayChoice.setTypeface(Typeface.DEFAULT);
    }

    //Show recipe list section
    private void openRecipeList(Bundle extras) {
        // Create an Intent to start the Recipes activity
        Intent intent = new Intent(Planner.this, RecipeList.class);

        startActivity(intent);
    }

    private void savePlanner() {
        String savedTimeChoice = String.valueOf(timeChoice.getText());
        String savedDayChoice = String.valueOf(dayChoice.getText());


        SharedPreferences sharedPreferences = getSharedPreferences("plannerSelections", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("savedTimeChoice", savedTimeChoice);
        editor.putString("savedDayChoice", savedDayChoice);

        boolean isSaved = editor.commit();

    }

    private void loadPlanner() {
        SharedPreferences sharedPreferences = getSharedPreferences("plannerSelections", Context.MODE_PRIVATE);
        String savedTimeChoice = sharedPreferences.getString("savedTimeChoice", "Choose a Time...");
        String savedDayChoice = sharedPreferences.getString("savedDayChoice", "Choose a Day...");

        timeChoice.setText(savedTimeChoice);
        dayChoice.setText(savedDayChoice);

    }
}


