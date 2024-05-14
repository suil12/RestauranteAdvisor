package com.example.prueba2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba2.adapter.RestaurantAdapter;
import com.example.prueba2.model.Restaurant;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class ManuActivity extends AppCompatActivity {
    RecyclerView recview;

        private RestaurantAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_restaurants);

            recview = findViewById(R.id.recview);
            recview.setLayoutManager(new LinearLayoutManager(this));

            FirebaseRecyclerOptions<Restaurant> options =
                    new FirebaseRecyclerOptions.Builder<Restaurant>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("restaurants"), Restaurant.class)
                            .build();

            adapter = new RestaurantAdapter(options);
            recview.setAdapter(adapter);

            adapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // Ottieni il ristorante dalla posizione selezionata
                    Restaurant selectedRestaurant = adapter.getItem(position);
                    if (selectedRestaurant != null) {
                        // Avvia l'attività ViewRestaurantActivity passando i dati del ristorante come extra
                        Intent intent = new Intent(ManuActivity.this, ViewRestaurantActivity.class);
                        intent.putExtra("restaurant_name", selectedRestaurant.getName());
                        intent.putExtra("restaurant_phone", selectedRestaurant.getPhone());
                        intent.putExtra("restaurant_address", selectedRestaurant.getAddress());
                        intent.putExtra("restaurant_category", selectedRestaurant.getCategory());
                        intent.putExtra("restaurant_photoUrl", selectedRestaurant.getPhotoUrl());
                        intent.putExtra("restaurant_cordX", selectedRestaurant.getCordX());
                        intent.putExtra("restaurant_cordY", selectedRestaurant.getCordY());
                        startActivity(intent);
                    } else {
                        Toast.makeText(ManuActivity.this, "Errore: impossibile ottenere i dati del ristorante", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}