package com.example.reciperealm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {

    TextInputEditText editTextUsername, editTextUserId, editTextDate_of_Birth, editTextFood_Preference, editTextEmail;
    Button buttonSave;
    ProgressBar progressBar;

    TextView textViewEdit;

    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        editTextUsername = findViewById(R.id.username);
        editTextUserId = findViewById(R.id.user_id);
        editTextEmail = findViewById(R.id.profile_email);
        editTextDate_of_Birth = findViewById(R.id.date_of_birth);
        editTextFood_Preference = findViewById(R.id.food_preference);
        buttonSave = findViewById(R.id.btn_save);
        progressBar = findViewById(R.id.progress_bar);
        textViewEdit = findViewById(R.id.edit_profile);

        loadProfile();

        textViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleEditMode();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfile();
            }
        });

        // Get user details from Firebase Authentication
        getUserDetails();
    }

    private void getUserDetails() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            String userId = user.getUid();
            // Set email and userId in the corresponding fields
            editTextEmail.setText(email);
            editTextUserId.setText(userId);
        }
    }

    private void saveProfile() {
        progressBar.setVisibility(View.VISIBLE);

        String username = getTextSafely(editTextUsername);
        String userId = getTextSafely(editTextUserId);
        String email = getTextSafely(editTextEmail);
        String date_of_birth = getTextSafely(editTextDate_of_Birth);
        String food_preference = getTextSafely(editTextFood_Preference);

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userId) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(date_of_birth) || TextUtils.isEmpty(food_preference)) {
            Toast.makeText(Profile.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("userId", userId);
        editor.putString("email", email);
        editor.putString("date_of_birth", date_of_birth);
        editor.putString("food_preference", food_preference);

        boolean isSaved = editor.commit();
        progressBar.setVisibility(View.GONE);
        if (isSaved) {
            Toast.makeText(Profile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
            toggleEditMode(); // Exit edit mode after saving
        } else {
            Toast.makeText(Profile.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }

    private String getTextSafely(TextInputEditText editText) {
        return editText != null ? editText.getText().toString().trim() : "";
    }

    private void loadProfile() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String userId = sharedPreferences.getString("userId", "");
        String email = sharedPreferences.getString("email", "");
        String date_of_birth = sharedPreferences.getString("date_of_birth", "");
        String food_preference = sharedPreferences.getString("food_preference", "");

        editTextUsername.setText(username);
        editTextUserId.setText(userId);
        editTextEmail.setText(email);
        editTextDate_of_Birth.setText(date_of_birth);
        editTextFood_Preference.setText(food_preference);
    }

    private void toggleEditMode() {
        if (!isEditMode) {
            enableEditMode();
            textViewEdit.setText("Cancel");
        } else {
            disableEditMode();
            textViewEdit.setText("Edit");
            loadProfile(); // Reset fields to original values
        }
        isEditMode = !isEditMode;
    }

    private void enableEditMode() {
        editTextUsername.setEnabled(true);
        editTextUserId.setEnabled(true);
        editTextEmail.setEnabled(true);
        editTextDate_of_Birth.setEnabled(true);
        editTextFood_Preference.setEnabled(true);
        buttonSave.setVisibility(View.VISIBLE);
    }

    private void disableEditMode() {
        editTextUsername.setEnabled(false);
        editTextUserId.setEnabled(false);
        editTextEmail.setEnabled(false);
        editTextDate_of_Birth.setEnabled(false);
        editTextFood_Preference.setEnabled(false);
        buttonSave.setVisibility(View.GONE);
    }
}


