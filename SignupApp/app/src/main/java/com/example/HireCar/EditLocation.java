package com.example.HireCar;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class EditLocation extends AppCompatActivity {

    Button editBtn, delBtn;
    String getLocName, getLocName2;
    TextInputEditText Location_name;
    TextView editTitle;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_locations);

        db = FirebaseFirestore.getInstance();
        Location_name = findViewById(R.id.Location_name);
        editTitle = findViewById(R.id.LocTitle);

        editBtn = findViewById(R.id.add_new_locaiton);
        delBtn = findViewById(R.id.del_location);

        editTitle.setText("Edit Location");
        editBtn.setText("Submit");
        Location_name.setText(getIntent().getStringExtra("LocName"));

        getLocName = getIntent().getStringExtra("LocName");
//        Toast.makeText(EditLocation.this, "Location is: " + getLocName, Toast.LENGTH_SHORT).show();

//        if(getLocName.equals("edit")){
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.collection("Location")
                            .whereEqualTo("Name", getLocName)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d("loc", document.getId() + " => " + document.getData());
                                            db.collection("Location").document(document.getId()).update("Name", Location_name.getText().toString());
                                        }
                                    } else {
                                        Log.d("er", "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                    Intent intent = new Intent(EditLocation.this, ViewLocations.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
//        }
//        else if(getLocName.equals("delete"))
//        {
//            delBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    db.collection("Location")
//                            .whereEqualTo("Name", getLocName2)
//                            .get()
//                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                    if (task.isSuccessful()) {
//                                        for (QueryDocumentSnapshot document : task.getResult()) {
//                                            Log.d("loc", document.getId() + " => " + document.getData());
//                                            db.collection("Location").document(document.getId()).delete()
//                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                        @Override
//                                                        public void onSuccess(Void unused) {
//                                                            Toast.makeText(EditLocation.this, "Location Deleted", Toast.LENGTH_SHORT).show();
//                                                            Intent intent = new Intent(EditLocation.this, ViewLocations.class);
//                                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                                            startActivity(intent);
//                                                        }
//                                                    });
//                                        }
//                                    } else {
//                                        Log.d("er", "Error getting documents: ", task.getException());
//                                    }
//                                }
//                            });
//                }
//            });

//        }

//        db.collection("Location").document(car_id[0]).update("available_flag","false");




    }
}
