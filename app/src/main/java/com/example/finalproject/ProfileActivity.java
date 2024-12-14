package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {
    private TextView profileName, profileEmail;
    private Button logoutButton;
    private FirebaseAuth auth;
    private View profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        logoutButton = findViewById(R.id.logoutButton);
        auth = FirebaseAuth.getInstance();

        // Fetch user details
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            profileName.setText(currentUser.getDisplayName() != null ? currentUser.getDisplayName() : "Name not set");
            profileEmail.setText(currentUser.getEmail());
        } else {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show();
            redirectToLogin();
        }

        // Logout functionality
        logoutButton.setOnClickListener(v -> {
            auth.signOut();
            Toast.makeText(ProfileActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            redirectToLogin();
        });

        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

    }

    private void redirectToLogin() {
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
