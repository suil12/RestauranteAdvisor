package com.example.prueba2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba2.adapter.RestaurantAdapter;
import com.example.prueba2.model.Restaurant;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainHomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RestaurantAdapter adapter;
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;

    ImageButton signout_btn, home_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerView);

        signout_btn = findViewById(R.id.signout_btn);

        home_button = findViewById(R.id.home_button);

        // Mostrar el nombre de usuario
        TextView userNameTextView = findViewById(R.id.user_name);
        String userName = obtenerNombreDeUsuario();
        userNameTextView.setText("Hola, " + userName);
        userNameTextView.setVisibility(View.VISIBLE);

        // Mostrar el dropdown y el botón de cerrar sesión solo si hay un usuario autenticado
        if (usuarioEstaAutenticado()) {
            findViewById(R.id.user_dropdown).setVisibility(View.VISIBLE);
            findViewById(R.id.signout_btn).setVisibility(View.VISIBLE);
        }

        setUpRecyclerView();


        signout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(MainHomeActivity.this, LoginActivity.class));
            }
        });

        signout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(MainHomeActivity.this, LoginActivity.class));
            }
        });
    }

    private void setUpRecyclerView() {
        Query query = firestore.collection("restaurants");

        FirestoreRecyclerOptions<Restaurant> options =
                new FirestoreRecyclerOptions.Builder<Restaurant>()
                        .setQuery(query, Restaurant.class)
                        .build();

        adapter = new RestaurantAdapter(options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }



    // Método para obtener el nombre de usuario
    private String obtenerNombreDeUsuario() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getDisplayName();
        } else {
            return "Usuario"; // Si no se puede obtener el nombre de usuario, devolver un valor predeterminado
        }
    }

    // Método para verificar si el usuario está autenticado
    private boolean usuarioEstaAutenticado() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user != null; // Devuelve true si el usuario está autenticado, false de lo contrario
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
