package com.example.prueba2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.prueba2.adapter.RestaurantAdapter;
import com.example.prueba2.model.Restaurant;
import com.example.prueba2.model.Review;
import com.example.prueba2.adapter.ReviewAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.Query;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ViewRestaurantActivity extends AppCompatActivity {
    private TextView textViewName;
    private TextView textViewPhone;
    private TextView textViewCategory;
    private TextView textViewAddress;

    private ImageView imageViewPic;
    private RecyclerView revview;
    private ReviewAdapter adapter;
    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurant);


        // Inizializza le viste
        textViewName = findViewById(R.id.textViewName);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewCategory = findViewById(R.id.textViewCategory);
        textViewAddress = findViewById(R.id.add);
        imageViewPic = findViewById(R.id.imageViewPic);



        // Recupera i dati passati dall'Intent
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("restaurant_name");
            String phone = intent.getStringExtra("restaurant_phone");
            String category = intent.getStringExtra("restaurant_category");
            String address = intent.getStringExtra("restaurant_address");
            String photoUrl = intent.getStringExtra("restaurant_photoUrl");

            // Imposta i dati nelle viste
            textViewName.setText(name);
            textViewPhone.setText(phone);
            textViewCategory.setText(category);
            textViewAddress.setText(address);
            Glide.with(imageViewPic.getContext()).load(photoUrl).into(imageViewPic);
        }

        String name = getIntent().getStringExtra("restaurant_name");

        findViewById(R.id.btn_rev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewRestaurantActivity.this, CreateReviewActivity.class);
                intent.putExtra("restaurant_name", name);
                startActivity(intent);
            }
        });


        revview = findViewById(R.id.revview);
        revview.setLayoutManager(new LinearLayoutManager(this));

        // Assuming you have a DatabaseReference for reviews
        String restaurantname = getIntent().getStringExtra("restaurant_name");

        FirebaseRecyclerOptions<Review> options =
                new FirebaseRecyclerOptions.Builder<Review>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("reviews").orderByChild("restaurantname").equalTo(restaurantname), Review.class)
                        .build();

        adapter = new ReviewAdapter(options);
        revview.setAdapter(adapter);
/*
        adapter.setOnItemClickListener(new ReviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click if needed
            }
        });
    */
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