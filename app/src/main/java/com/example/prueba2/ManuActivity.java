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


public class ManuActivity extends AppCompatActivity {

        private RecyclerView recyclerView;
        private RestaurantAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_restaurants);




            recyclerView = findViewById(R.id.restaurantList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            FirebaseRecyclerOptions<Restaurant> options =
                    new FirebaseRecyclerOptions.Builder<Restaurant>()
                            .setQuery(FirebaseDatabase.getInstance().getReference("restaurants"), Restaurant.class)
                            .build();


            CardView cardView2;
            cardView2= findViewById(R.id.cardViewWidget2);
            cardView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ManuActivity.this, ViewRestaurantActivity.class));

                }
            });

            adapter = new RestaurantAdapter(options);
            recyclerView.setAdapter(adapter);

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

/*    private RecyclerView recyclerView;
    private DatabaseReference database;
    private RestaurantAdapter adapter;
    ArrayList<Restaurant> list;
    // private FirebaseRecyclerAdapter<Restaurant, RestaurantViewHolder> adapter;


    //@SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        recyclerView = findViewById(R.id.restaurantList);
        database = FirebaseDatabase.getInstance().getReference("Restaurants");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        restaurantAdapter = new RestaurantAdapter(this, list);
        recyclerView.setAdapter(restaurantAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                    list.add(restaurant);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
    }*/


/*
        //mFirestore = FirebaseFirestore.getInstance();
        //mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


       //btn_add = findViewById(R.id.btn_add);

        CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManuActivity.this, CreatePhotoActivity.class));
            }
        });



       // setUpRecyclerView();
        //search_view();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("restaurants");

        // Configura las opciones para el adaptador FirebaseRecyclerAdapter
        FirebaseRecyclerOptions<Restaurant> options =
                new FirebaseRecyclerOptions.Builder<Restaurant>()
                        .setQuery(databaseReference, Restaurant.class)
                        .build();

        // Inicializa el adaptador FirebaseRecyclerAdapter
        adapter = new FirebaseRecyclerAdapter<Restaurant, RestaurantViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position, @NonNull Restaurant model) {
                // Configura los detalles del restaurante en la vista
                holder.setDetails(model.getName(), model.getPhone(), model.getCategory(), model.getAddress(), model.calculateAverageRating());
            }

            @NonNull
            @Override
            public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Infla el diseño de la tarjeta del restaurante
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
                // Retorna una instancia de RestaurantViewHolder
                return new RestaurantViewHolder(view);
            }
        };
/
        // Establece el adaptador en el RecyclerView
        recyclerView.setAdapter(adapter);*/


    /*@SuppressLint("NotifyDataSetChanged")
    private void setUpRecyclerView() {
        mRecycler = findViewById(R.id.recyclerView);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        query = mFirestore.collection("restaurants");

        FirestoreRecyclerOptions<Restaurant> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Restaurant>().setQuery(query, Restaurant.class).build();

        mAdapter = new RestaurantAdapter(firestoreRecyclerOptions, this, getSupportFragmentManager());
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
    }*/

   /* private void search_view() {
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
    }*/
/*
    @Override
    protected *void onStart() {
        super.onStart();
      //  adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
     //   adapter.stopListening();
    }

 */
    /*
    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        // Método para establecer los detalles del restaurante en la vista
        public void setDetails(String name, String phone, String category, String address, double averageRating) {

            TextView textViewName = mView.findViewById(R.id.textViewName);
            TextView textViewPhone = mView.findViewById(R.id.textViewPhone);
            TextView textViewCategory = mView.findViewById(R.id.textViewCategory);
            TextView textViewAddress = mView.findViewById(R.id.textViewAddress);
            TextView textViewAverageRating = mView.findViewById(R.id.textViewAverageRating);

            textViewName.setText(name);
            textViewPhone.setText(phone);
            textViewCategory.setText(category);
            textViewAddress.setText(address);
            textViewAverageRating.setText(String.format(Locale.getDefault(), "%.2f", averageRating));
        }
    }}*/

