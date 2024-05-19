package com.example.reciperealm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //Declare UI elements
    private Button recipeListbtn, plannerListbtn, plannerbtn, profilebtn, logoutbtn;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        recipeListbtn = findViewById(R.id.RecipeList);
        plannerListbtn = findViewById(R.id.PlannerList);
        plannerbtn = findViewById(R.id.Planner);
        profilebtn = findViewById(R.id.Profile);
        logoutbtn = findViewById(R.id.Logout);

        //Set onClickListener for "RecipeList" Button
        recipeListbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open RecipeList activity
                Intent i = new Intent(MainActivity.this, RecipeList.class);
                startActivity(i);
            }
        });
        //Set onClickListener for "PlannerList" Button
        plannerListbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open PlannerList activity
                Intent i = new Intent(MainActivity.this, PlannerList.class);
                startActivity(i);
            }
        });
        //Set onClickListener for "Planner" Button
        plannerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Planner activity
                Intent i = new Intent(MainActivity.this, Planner.class);
                startActivity(i);
            }
        });
        //Set onClickListener for "Profile" Button
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Profile activity
                Intent i = new Intent(MainActivity.this, Profile.class);
                startActivity(i);
            }
        });
        //Set onClickListener for "Logout" Button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign out the user and open Login activity
                mAuth.signOut();
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
                finish();  // Close the MainActivity so user can't return to it by pressing back button
            }
        });
    }
}
