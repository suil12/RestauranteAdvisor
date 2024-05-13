package com.example.prueba2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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