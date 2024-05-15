package com.example.reciperealm;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class RecipesPage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_page);

        Bundle extras = getIntent().getExtras();


        TextView pageRecipeName = (TextView)findViewById(R.id.pageRecipeName);
        TextView pageRecipeDesc = (TextView)findViewById(R.id.pageRecipeDesc);
        TextView pageRecipeIngredients = (TextView)findViewById(R.id.pageRecipeIngredients);
        TextView pageRecipeInstructions = (TextView)findViewById(R.id.pageRecipeInstructions);

        pageRecipeName.setText(extras.getString("recipeName"));
        pageRecipeDesc.setText(extras.getString("recipeDesc"));
        pageRecipeIngredients.setText(extras.getString("recipeIngredients"));
        pageRecipeInstructions.setText(extras.getString("recipeInstruct"));


    }
}