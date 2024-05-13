package com.example.prueba2.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba2.R;
import com.example.prueba2.model.Restaurant;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RestaurantAdapter extends FirebaseRecyclerAdapter<Restaurant, RestaurantAdapter.resviewHolder> {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @NonNull
    @Override
    public resviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        return new resviewHolder(view);
    }

     static class resviewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        TextView name, address, category, phone;
      //  ImageView img;
        public resviewHolder(@NonNull View itemView) {
            super(itemView);
            //img=(ImageView)itemView.findViewById(R.id.imgRest);
            name=itemView.findViewById(R.id.textViewName);
            address=itemView.findViewById(R.id.textViewAddress);
            category=itemView.findViewById(R.id.textViewCategory);
            phone=itemView.findViewById(R.id.textViewPhone);
            cardView=itemView.findViewById(R.id.cardViewRes);
        }
    }

}