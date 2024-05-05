package com.example.prueba2.adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba2.CreatePhotoActivity;
import com.example.prueba2.PhotoDetailActivity;
import com.example.prueba2.R;
import com.example.prueba2.model.Foto;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class FotoAdapter extends FirestoreRecyclerAdapter<Foto, FotoAdapter.ViewHolder> {





    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

    Activity activity;

    FragmentManager fm;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     * @param supportFragmentManager
     */
    public FotoAdapter(@NonNull FirestoreRecyclerOptions<Foto> options, Activity activity, FragmentManager supportFragmentManager) {
        super(options);
        this.activity = activity ;
        this.fm = fm;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_photo, parent, false);
        return new ViewHolder(v);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Foto foto) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        viewHolder.nombre.setText(foto.getNombre());
        viewHolder.precio.setText(foto.getPrecio());
        viewHolder.descripcion.setText(foto.getDescripcion());


        String photoPet = foto.getPhoto();
        try {
            if (!TextUtils.isEmpty(photoPet)) {
                Picasso.with(activity.getApplicationContext())
                        .load(photoPet)
                        .resize(150, 150)
                        .into(viewHolder.photo_pet);
            }
        } catch (Exception e) {
            Log.d("Exception", "e: " + e);
        }


        viewHolder.btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePet(id);
            }
        });

        viewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, CreatePhotoActivity.class);
                i.putExtra("id_foto", id);
                i.putExtra("modo_edicion", true);
                activity.startActivity(i);
            }
        });

        viewHolder.photo_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, PhotoDetailActivity.class);
                i.putExtra("id_foto", id);
                i.putExtra("modo_edicion", false);
                activity.startActivity(i);
            }
        });


    }

    private void deletePet(String id) {
        mFirestore.collection("photos").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged(); // Agregar esta línea para actualizar la lista
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio, descripcion;
        ImageView btn_eliminar ,btn_edit, photo_pet;

        TextView coordenadx, coordenaday; // Agrega estas dos variables


        //Button btn_add;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            precio = itemView.findViewById(R.id.precio);
            descripcion = itemView.findViewById(R.id.descripcion);
            photo_pet = itemView.findViewById(R.id.photo);
            btn_eliminar = itemView.findViewById(R.id.btn_eliminar);
            btn_edit = itemView.findViewById(R.id.btn_editar);
            coordenadx = itemView.findViewById(R.id.coordenadax); // Asigna la vista correspondiente
            coordenaday = itemView.findViewById(R.id.coordenaday);

            //btn_add = itemView.findViewById(R.id.btn_add);


        }
    }
}
