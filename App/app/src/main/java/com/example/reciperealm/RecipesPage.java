package com.example.reciperealm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Objects;

public class RecipesPage extends AppCompatActivity {
    // Declare UI elements
    Button addToPlannerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_page);
        //Get Intent information passed through.
        Bundle extras = getIntent().getExtras();
        //User can't come to page with an empty intent but added for precaution
        assert extras != null;
        //Initialise UI elements
        TextView pageRecipeName = (TextView)findViewById(R.id.pageRecipeName);
        TextView pageRecipeDesc = (TextView)findViewById(R.id.pageRecipeDesc);
        TextView pageRecipeIngredients = (TextView)findViewById(R.id.pageRecipeIngredients);
        TextView pageRecipeInstructions = (TextView)findViewById(R.id.pageRecipeInstructions);
        addToPlannerBtn = findViewById(R.id.addToPlanner);
        //If User is coming from PlanList remove addToPlanner button
        if(Objects.equals(extras.getString("PlanToken"), "true")){
            addToPlannerBtn.setVisibility(View.GONE);
        }

        //Set the information from Intent into TextViews
        pageRecipeName.setText(extras.getString("recipeName"));
        pageRecipeDesc.setText(extras.getString("recipeDesc"));
        pageRecipeIngredients.setText(extras.getString("recipeIngredients"));
        pageRecipeInstructions.setText(extras.getString("recipeInstruct"));

        //Redirect to Planner activity, Recipe Details are passed through.
        addToPlannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipesPage.this, Planner.class);
                intent.putExtra("recipeName", extras.getString("recipeName"));
                intent.putExtra("recipeDesc", extras.getString("recipeDesc"));
                intent.putExtra("recipeIngredients", extras.getString("recipeIngredients"));
                intent.putExtra("recipeInstructions", extras.getString("recipeInstruct"));
                startActivity(intent);
            }
        });
    }
}