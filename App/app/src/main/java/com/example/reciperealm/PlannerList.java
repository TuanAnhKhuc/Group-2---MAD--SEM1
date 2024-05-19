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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Objects;

public class PlannerList extends AppCompatActivity {

    //Declare UI elements
    RecyclerView recyclerView;
    ArrayList<Plan> planArrayList;
    PlanAdapter planAdapter;
    FirebaseFirestore db;
    FirebaseAuth fAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner_list);
        //Initialise UI Elements
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
        recyclerView = findViewById(R.id.plannerRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Initialise FireBase
        db = FirebaseFirestore.getInstance();
        //Initialise ArrayList
        planArrayList = new ArrayList<>();
        planAdapter = new PlanAdapter(PlannerList.this, planArrayList);
        //set Adapter for RecyclerView
        recyclerView.setAdapter(planAdapter);

        EventChangeListener();

        //Declare and Initialise Home Button
        Button homeBtn = findViewById(R.id.homeButton);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlannerList.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //Command to query data from FireStore
    private void EventChangeListener() {
        //Initialise Firebase Authentication
        fAuth =FirebaseAuth.getInstance();
        //Set User ID
        String id = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        //Retrieve data from FireStore
        CollectionReference plansdb = db.collection("plans");
        //Query Data by looking for only data that match the Users ID
        Query query = plansdb.whereEqualTo("userID", id);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                                planArrayList.add(dc.getDocument().toObject(Plan.class));
                            }
                            planAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing()){
                                //dismiss progress bar
                                progressDialog.dismiss();
                            }

                        }

                    }
                });

    }
}