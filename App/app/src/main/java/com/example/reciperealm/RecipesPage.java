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

public class RecipesPage extends AppCompatActivity {

    Button addToPlannerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_page);

        Bundle extras = getIntent().getExtras();

        TextView pageRecipeName = (TextView)findViewById(R.id.pageRecipeName);
        TextView pageRecipeDesc = (TextView)findViewById(R.id.pageRecipeDesc);
        TextView pageRecipeIngredients = (TextView)findViewById(R.id.pageRecipeIngredients);
        TextView pageRecipeInstructions = (TextView)findViewById(R.id.pageRecipeInstructions);
        addToPlannerBtn = findViewById(R.id.addToPlanner);

        pageRecipeName.setText(extras.getString("recipeName"));
        pageRecipeDesc.setText(extras.getString("recipeDesc"));
        pageRecipeIngredients.setText(extras.getString("recipeIngredients"));
        pageRecipeInstructions.setText(extras.getString("recipeInstruct"));

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