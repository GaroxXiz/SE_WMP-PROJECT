package com.example.finalproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class InventoryListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference inventoryRef;
    private ArrayList<InventoryItem> inventoryList;
    private InventoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        inventoryList = new ArrayList<>();
        adapter = new InventoryAdapter(inventoryList);

        recyclerView.setAdapter(adapter);
        database = FirebaseDatabase.getInstance();
        inventoryRef = database.getReference("inventory");

        // Fetch data from Firebase
        inventoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                inventoryList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    InventoryItem item = dataSnapshot.getValue(InventoryItem.class);
                    inventoryList.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(InventoryListActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
