package com.example.prueba2.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba2.R;
import com.example.prueba2.model.Restaurant;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
public class RestaurantAdapter extends FirebaseRecyclerAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder> {
    public RestaurantAdapter(@NonNull FirebaseRecyclerOptions<Restaurant> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position, @NonNull Restaurant model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        return new RestaurantViewHolder(view);
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewPhone;
        private TextView textViewAddress;
        private TextView textViewCategory;
        private RatingBar ratingBar;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            //ratingBar = itemView.findViewById(R.id.ratingBar);
        }

        public void bind(Restaurant restaurant) {
            textViewName.setText(restaurant.getName());
            textViewPhone.setText(restaurant.getPhone());
            textViewAddress.setText(restaurant.getAddress());
            textViewCategory.setText(restaurant.getCategory());
            //ratingBar.setRating(restaurant.getRating());
        }
    }
}
/*
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba2.R;

import com.example.prueba2.model.Restaurant;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {


    Context context;
    ArrayList<Restaurant> list;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.restaurant_card, parent, false);
        return new RestaurantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {

        Restaurant restaurant= list.get(position);
        holder.name.setText(restaurant.getName());
        holder.address.setText(restaurant.getAddress());
        holder.phone.setText(restaurant.getPhone());
        holder.category.setText(restaurant.getCategory());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder{
        TextView name, phone, category, address;


        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            phone = itemView.findViewById(R.id.textViewPhone);
            category = itemView.findViewById(R.id.textViewCategory);
            address = itemView.findViewById(R.id.textViewAddress);
           // rating = itemView.findViewById(R.id.rating);


        }

}



*/








/*
    public RestaurantAdapter(@NonNull FirestoreRecyclerOptions<Restaurant> options, Activity activity) {
        super(options);
        this.activity = activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Restaurant model) {
        holder.name.setText(model.getName());
        holder.phone.setText(model.getPhone());
        holder.category.setText(model.getCategory());
        holder.address.setText(model.getAddress());
       /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Apri l'activity dei dettagli del ristorante quando la card viene cliccata
                Intent intent = new Intent(activity, RestaurantDetailActivity.class);
                // Passa il ristorante selezionato all'activity dei dettagli del ristorante
                intent.putExtra("restaurant", model);
                activity.startActivity(intent);
            }
        });*/

        // Aquí se va a agregar un OnClickListener para cada tarjeta, para acceder a los detalles al hacer click.


/*
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, category, address, averageRating;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            phone = itemView.findViewById(R.id.textViewPhone);
            category = itemView.findViewById(R.id.textViewCategory);
            address = itemView.findViewById(R.id.textViewAddress);
            averageRating = itemView.findViewById(R.id.textViewAverageRating);
            cardView = itemView.findViewById(R.id.cardViewWidget);

        }

    }
}
*/
  