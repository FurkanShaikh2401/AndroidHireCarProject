package com.example.HireCar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewLocations extends AppCompatActivity {


    RecyclerView locrcv;
    ArrayList<LocationModel> locList;

    FirebaseFirestore db;
    LocationAdapter locAdapter;

    Button add_location;
    HashMap<String, String> LocIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);

        add_location = findViewById(R.id.add_location);
        locrcv = findViewById(R.id.locrecyclerview);


        locrcv.setLayoutManager(new LinearLayoutManager(this));
        locList = new ArrayList<>();
        locAdapter = new LocationAdapter(locList, getApplicationContext());
        locrcv.setAdapter(locAdapter);

        LocIds = new HashMap<>();
        db = FirebaseFirestore.getInstance();
        db.collection("Location").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            LocationModel lm = new LocationModel();
                            lm.setLocation_name(d.getString("Name"));
                            locList.add(lm);
                        }
                        locAdapter.notifyDataSetChanged();
                    }
                });

        add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewLocations.this, Add_Locations.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}