package com.example.finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddItemActivity extends AppCompatActivity {
    private EditText itemNameField, itemQuantityField;
    private Button addItemButton;
    private FirebaseDatabase database;
    private DatabaseReference inventoryRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemNameField = findViewById(R.id.itemNameField);
        itemQuantityField = findViewById(R.id.itemQuantityField);
        addItemButton = findViewById(R.id.addItemButton);
        database = FirebaseDatabase.getInstance();
        inventoryRef = database.getReference("inventory");

        addItemButton.setOnClickListener(v -> {
            String name = itemNameField.getText().toString().trim();
            String quantity = itemQuantityField.getText().toString().trim();

            if (!name.isEmpty() && !quantity.isEmpty()) {
                String id = inventoryRef.push().getKey();
                InventoryItem newItem = new InventoryItem(id, name, Integer.parseInt(quantity));
                inventoryRef.child(id).setValue(newItem)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(this, "Item added successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> Toast.makeText(this, "Failed to add item.", Toast.LENGTH_SHORT).show());
            } else {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
