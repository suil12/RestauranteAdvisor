package com.example.prueba2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.content.Context;
import android.os.AsyncTask;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.example.prueba2.adapter.FotoAdapter;
import com.example.prueba2.model.Foto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;









public class CreatePhotoActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    ImageView photo_pet;
    Button btn_add;

    Button btn_maps;

    Button btn_cu_photo, btn_r_photo;

    LinearLayout LinearLayout_image_btn;
    EditText nombre,descripcion, coordenadax, coordenaday, precio;



    private FirebaseAuth mAuth;
    private FirebaseFirestore mfirestore;



    LinearLayout linearLayout_image_btn;

    StorageReference storageReference;

    private StorageReference mStorageRef;

    String storage_path = "photos/*";

    private static final int COD_SEL_STORAGE = 200;
    private static final int COD_SEL_IMAGE = 300;

    private Uri image_url;
    String photo = "photo";
    String idd = UUID.randomUUID().toString(); // Inicializar aquí

    ProgressDialog progressDialog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_photo);

        Toolbar toolbarLayout = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbarLayout);

        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title
        getSupportActionBar().setTitle("Review");

        // Set the back button click listener
        ImageButton backButton = toolbarLayout.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        boolean modoEdicion = getIntent().getBooleanExtra("modo_edicion", false);

        this.setTitle("Lugares");
        ActionBar actionBar = getSupportActionBar();





        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        progressDialog = new ProgressDialog(this);
        String id = getIntent().getStringExtra("id_foto");
        mfirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        mfirestore = FirebaseFirestore.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference().child("photos");


        linearLayout_image_btn = findViewById(R.id.images_btn);
        nombre = findViewById(R.id.nombre);
        descripcion = findViewById(R.id.descripcion);
        coordenadax = findViewById(R.id.coordenadax);
        coordenaday = findViewById(R.id.coordenaday);
        precio = findViewById(R.id.precio);



        photo_pet = findViewById(R.id.pet_photo);
        btn_cu_photo = findViewById(R.id.btn_photo);
        btn_r_photo = findViewById(R.id.btn_remove_photo);





        btn_add = findViewById(R.id.btn_add);

        btn_maps = findViewById(R.id.btn_maps);





        btn_cu_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPhoto();
            }
        });


        btn_r_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("photo", "");
                mfirestore.collection("photos").document(idd).update(map);
                Toast.makeText(CreatePhotoActivity.this, "Foto eliminada", Toast.LENGTH_SHORT).show();
            }
        });

        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfirestore.collection("photos").document(idd).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String latitudeString = documentSnapshot.getString("coordenadax");
                        String longitudeString = documentSnapshot.getString("coordenaday");
                        String label = documentSnapshot.getString("nombre");

                        if (latitudeString != null && longitudeString != null) {
                            latitudeString = latitudeString.replace("\"", "");
                            longitudeString = longitudeString.replace("\"", "");

                            if (!latitudeString.isEmpty() && !longitudeString.isEmpty()) {
                                double latitude = Double.parseDouble(latitudeString);
                                double longitude = Double.parseDouble(longitudeString);

                                String uriBegin = "geo:" + latitude + "," + longitude;
                                String query = latitude + "," + longitude + "(" + label + ")";
                                String encodedQuery = Uri.encode(query);
                                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                                Uri uri = Uri.parse(uriString);
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(mapIntent);
                            } else {
                                Toast.makeText(getApplicationContext(), "No se encontraron coordenadas de ubicación", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No se encontraron coordenadas de ubicación", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al obtener los datos de ubicación", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });









        if (id == null || id == ""){
            linearLayout_image_btn.setVisibility(View.GONE);
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nombr = nombre.getText().toString().trim();
                    String descripcio = descripcion.getText().toString().trim();
                    String coordenadasx = coordenadax.getText().toString().trim();
                    String coordenadasy= coordenaday.getText().toString().trim();
                    String preci = precio.getText().toString().trim();

                    if(nombr.isEmpty() && descripcio.isEmpty() && coordenadasx.isEmpty() && coordenadasy.isEmpty() && preci.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                    }else{
                        postPhoto(nombr, descripcio, coordenadasx, coordenadasy, preci);
                    }
                }
            });
        }else{
            idd = id;
            btn_add.setText("Update");
            getPet(id);
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nombr = nombre.getText().toString().trim();
                    String descripcio = descripcion.getText().toString().trim();
                    String coordenadasx = coordenadax.getText().toString().trim();
                    String coordenadasy= coordenaday.getText().toString().trim();
                    String preci = precio.getText().toString().trim();

                    if(nombr.isEmpty() && descripcio.isEmpty() && coordenadasx.isEmpty() && coordenadasy.isEmpty() && preci.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                    }else{
                        updatePhoto(nombr, descripcio, coordenadasx, coordenadasy, preci, id);
                    }
                }
            });
        }
    }




    private void postPhoto(String nombr, String descripcio, String coordenadasx, String coordenadasy, String preci) {
        String idUser = mAuth.getCurrentUser().getUid();
        DocumentReference id = mfirestore.collection("photos").document();

        Map<String, Object> map = new HashMap<>();
        map.put("id_user", idUser);
        map.put("id", id.getId());
        map.put("nombre", nombr);
        map.put("descripcion", descripcio);
        map.put("coordenadax", coordenadasx);
        map.put("coordenaday", coordenadasy);
        map.put("precio", preci);

        mfirestore.collection("photos").document(id.getId()).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
// Aquí procesamos el resultado obtenido
                    if (result != null) {
// Manejamos la imagen seleccionada
                        subirPhoto(result);
                    } else {
// Si el usuario canceló la selección, mostramos un mensaje de error o algo similar
                        Toast.makeText(CreatePhotoActivity.this, "Error al seleccionar imagen", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    // Luego, en el método uploadPhoto(), reemplazamos la llamada a startActivityForResult con el método launch() del objeto launcher que acabamos de crear:
    private void uploadPhoto() {
        launcher.launch("image/*");
    }

    // En el método onActivityResult(), eliminamos la línea image_url = data.getData();
// ya que la imagen seleccionada ahora se pasa directamente al método subirPhoto()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST && data != null) {
                Uri imageUri = data.getData();
                subirPhoto(imageUri);
            }
        }
    }


    // Actualizamos el método subirPhoto() para usar StorageReference.putFile() y StorageReference.getDownloadUrl()

    // Actualizamos el método subirPhoto() para usar StorageReference.putFile() y StorageReference.getDownloadUrl()


    // Primero, creamos un objeto ActivityResultLauncher para lanzar la actividad y recibir la respuesta


    // Actualizamos el método subirPhoto() para usar StorageReference.putFile() y StorageReference.getDownloadUrl()

    // Actualizamos el método subirPhoto() para usar StorageReference.putFile() y StorageReference.getDownloadUrl()
    private void subirPhoto(Uri image_url) {
        progressDialog.setMessage("Actualizando foto");
        progressDialog.show();
        String rute_storage_photo = storage_path + "" + photo + "" + mAuth.getUid() +""+ idd;
        StorageReference reference = storageReference.child(rute_storage_photo);
        reference.putFile(image_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                if (uriTask.isSuccessful()){
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String download_uri = uri.toString();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("photo", download_uri);
                            mfirestore.collection("photos").document(idd).update(map);
                            Toast.makeText(CreatePhotoActivity.this, "Foto actualizada", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreatePhotoActivity.this, "Error al cargar foto", Toast.LENGTH_SHORT).show();
            }
        });
    }







    // Método para actualizar una mascota en el Storage de Firebase
    private void updatePhoto(String nombr, String descripcio, String coordenadasx, String coordenadasy, String preci, String id) {

        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombr);
        map.put("descripcion", descripcio);
        map.put("coordenadax", coordenadasx);
        map.put("coordenaday", coordenadasy);
        map.put("precio", preci);

        mfirestore.collection("photos").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Actualizado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void getPet(String id){
        mfirestore.collection("photos").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                DecimalFormat format = new DecimalFormat("0.00");
                String nombrPhoto = documentSnapshot.getString("nombre");
                String descripcioPhoto = documentSnapshot.getString("descripcion");
                String coordenadasxPhoto = documentSnapshot.getString("coordenadax");
                String coordenadasyPhoto = documentSnapshot.getString("coordenaday");
                String preciPhoto = documentSnapshot.getString("precio");
                //String photoPet = documentSnapshot.getString("photo");
                String photoPet = documentSnapshot.getString("photo");



                nombre.setText(nombrPhoto);
                descripcion.setText(descripcioPhoto);
                coordenadax.setText(coordenadasxPhoto);
                coordenaday.setText(coordenadasyPhoto);
                precio.setText(preciPhoto);



                try {
                    if(!photoPet.equals("")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Cargando foto", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP,0,200);
                        toast.show();
                        Picasso.with(CreatePhotoActivity.this)
                                .load(photoPet)
                                .resize(150, 150)
                                .into(photo_pet);
                    }
                }catch (Exception e){
                    Log.v("Error", "e: " + e);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }





    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}