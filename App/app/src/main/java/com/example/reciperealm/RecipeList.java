package com.example.reciperealm;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Objects;

public class RecipeList extends AppCompatActivity {

    //Declare UI elements
    RecyclerView recyclerView;
    ArrayList<Recipes> recipesArrayList;
    RecipeAdapter recipeAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipelist);
        //Initialise UI Elements
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
        recyclerView = findViewById(R.id.RecipesRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Initialise FireBase
        db = FirebaseFirestore.getInstance();
        //Initialise ArrayList
        recipesArrayList = new ArrayList<>();
        recipeAdapter = new RecipeAdapter(RecipeList.this, recipesArrayList);
        //set Adapter for RecyclerView
        recyclerView.setAdapter(recipeAdapter);

        EventChangeListener();


        //Declare and Initialise Home Button
        Button homeBtn = findViewById(R.id.homeButton);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeList.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //Command to query data from FireStore
    private void EventChangeListener() {
        //Query Data in Recipes collection, Will be ordered by Name
        db.collection("Recipes").orderBy("Name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            //If there is an error, logs it and returns.
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            Log.e("FireStore error", Objects.requireNonNull(error.getMessage()));
                            return;
                        }

                        assert value != null;
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                //adds data to arraylist
                                recipesArrayList.add(dc.getDocument().toObject(Recipes.class));
                            }

                            recipeAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing()){
                                //dismiss progress bar
                                progressDialog.dismiss();
                            }

                        }

                    }
                });

    }
}
