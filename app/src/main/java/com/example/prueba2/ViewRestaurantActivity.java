package com.example.prueba2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class ViewRestaurantActivity extends AppCompatActivity {
    private TextView textViewName;
    private TextView textViewPhone;
    private TextView textViewCategory;
    private TextView textViewAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurant);




            // Inizializza le viste
            textViewName = findViewById(R.id.textViewName);
            textViewPhone = findViewById(R.id.textViewPhone);
            textViewCategory = findViewById(R.id.textViewCategory);
            textViewAddress = findViewById(R.id.add);

            // Recupera i dati passati dall'Intent
            Intent intent = getIntent();
            if (intent != null) {
                String name = intent.getStringExtra("restaurant_name");
                String phone = intent.getStringExtra("restaurant_phone");
                String category = intent.getStringExtra("restaurant_category");
                String address = intent.getStringExtra("restaurant_address");

                // Imposta i dati nelle viste
                textViewName.setText(name);
                textViewPhone.setText(phone);
                textViewCategory.setText(category);
                textViewAddress.setText(address);
            }
        findViewById(R.id.btn_rev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewRestaurantActivity.this, CreatePhotoActivity.class));
            }
        });
}
}