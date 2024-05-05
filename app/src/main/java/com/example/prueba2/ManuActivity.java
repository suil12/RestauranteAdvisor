package com.example.prueba2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba2.adapter.FotoAdapter;
import com.example.prueba2.adapter.PetAdapter;
import com.example.prueba2.model.Foto;
import com.example.prueba2.model.Pet;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class ManuActivity extends AppCompatActivity{

    Button btn_add;
    FotoAdapter mAdapter;
    RecyclerView mRecycler;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    SearchView search_view;
    Query query;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_foto_single);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        search_view = findViewById(R.id.search);

        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManuActivity.this, CreatePhotoActivity.class));
            }
        });








        setUpRecyclerView();
        search_view();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setUpRecyclerView() {
        mRecycler = findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        query = mFirestore.collection("photos");

        FirestoreRecyclerOptions<Foto> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Foto>().setQuery(query, Foto.class).build();

        mAdapter = new FotoAdapter(firestoreRecyclerOptions, this, getSupportFragmentManager());
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
    }

    private void search_view() {
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                textSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                textSearch(s);
                return false;
            }
        });
    }
    public void textSearch(String s){
        FirestoreRecyclerOptions<Foto> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Foto>()
                        .setQuery(query.orderBy("nombre")
                                .startAt(s).endAt(s+"~"), Foto.class).build();
        mAdapter = new FotoAdapter(firestoreRecyclerOptions, this, getSupportFragmentManager());
        mAdapter.startListening();
        mRecycler.setAdapter(mAdapter);
    }









    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}