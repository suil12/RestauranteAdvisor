package com.example.prueba2.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public class RestaurantAdapter extends FirebaseRecyclerAdapter<Restaurant, RestaurantAdapter.resviewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public RestaurantAdapter(@NonNull FirebaseRecyclerOptions<Restaurant> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull resviewHolder holder, int position, @NonNull Restaurant model) {

        holder.name.setText(model.getName());
        holder.phone.setText(model.getPhone());
        holder.address.setText(model.getAddress());
        holder.category.setText(model.getCategory());

    }

    @NonNull
    @Override
    public resviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        return new resviewHolder(view);
    }

     static class resviewHolder extends RecyclerView.ViewHolder
    {
        TextView name, address, category, phone;
      //  ImageView img;
        public resviewHolder(@NonNull View itemView) {
            super(itemView);
            //img=(ImageView)itemView.findViewById(R.id.imgRest);
            name=itemView.findViewById(R.id.textViewName);
            address=itemView.findViewById(R.id.textViewAddress);
            category=itemView.findViewById(R.id.textViewCategory);
            phone=itemView.findViewById(R.id.textViewPhone);
        }
    }

}