package com.example.prueba2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba2.R;
import com.example.prueba2.model.Restaurant;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RestaurantAdapter extends FirestoreRecyclerAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder> {

    public RestaurantAdapter(@NonNull FirestoreRecyclerOptions<Restaurant> options) {
        super(options);
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position, @NonNull Restaurant model) {
        // Bind data to views here
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        // Declare views here

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views here
        }
    }
}
