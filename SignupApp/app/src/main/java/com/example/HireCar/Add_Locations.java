package com.example.HireCar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Add_Locations extends AppCompatActivity {

    Button new_add_location_btn;
    TextInputEditText Location_name;
    String location_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_locations);

        Location_name = findViewById(R.id.Location_name);

//        location_name= Locatios_name.getText().toString();

        new_add_location_btn =findViewById(R.id.add_new_locaiton);
        new_add_location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Location_name.getText().toString().isEmpty())
                {
                    Toast.makeText(Add_Locations.this, "Email Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                addData();
            }
        });


    }

    public void addData(){

        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        Map<String, String> data = new HashMap<>();

        data.put("Name", Location_name.getText().toString());

        firebaseFirestore.collection("Location").add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Add_Locations.this, "loctions add"+Location_name.getText().toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Add_Locations.this, ViewLocations.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

    }
}