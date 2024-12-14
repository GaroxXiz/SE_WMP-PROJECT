package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    private Button inventoryListButton, addItemButton, sensorButton, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        inventoryListButton = findViewById(R.id.inventoryListButton);
        addItemButton = findViewById(R.id.addItemButton);
        sensorButton = findViewById(R.id.sensorButton);
        profileButton = findViewById(R.id.profileButton);

        inventoryListButton.setOnClickListener(v -> startActivity(new Intent(this, InventoryListActivity.class)));
        addItemButton.setOnClickListener(v -> startActivity(new Intent(this, AddItemActivity.class)));
        sensorButton.setOnClickListener(v -> startActivity(new Intent(this, SensorActivity.class)));
        profileButton.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
