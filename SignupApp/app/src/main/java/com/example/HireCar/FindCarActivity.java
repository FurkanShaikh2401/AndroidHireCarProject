package com.example.HireCar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindCarActivity extends AppCompatActivity {

    String pickuploc, droploc, start_date, end_date, start_time, end_time, finalDays, finalHours;

    TextView PickupLocation, DropLocation, carCount;

    RecyclerView Carrcv;
    CarAdapter carAdapter;
    FirebaseFirestore db;
    ArrayList<CarModel> carList;
    HashMap<String, String> carIds;
    int noOfCars = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_car);

        pickuploc = getIntent().getStringExtra("pickuplocation");
        droploc = getIntent().getStringExtra("droplocation");
        start_date = getIntent().getStringExtra("startdate");
        end_date = getIntent().getStringExtra("enddate");
        start_time = getIntent().getStringExtra("startingtime");
        end_time = getIntent().getStringExtra("endingtime");
        finalDays = getIntent().getStringExtra("finaldays");
        finalHours = getIntent().getStringExtra("finalhours");


        carCount = findViewById(R.id.noofcars);

        db = FirebaseFirestore.getInstance();
        carList = new ArrayList<>();
        carIds = new HashMap<>();

        Carrcv = (RecyclerView) findViewById(R.id.recyclerView2);
        Carrcv.setLayoutManager(new LinearLayoutManager(this));

        carAdapter = new CarAdapter(carList, carIds, getApplicationContext(), start_date, end_date, start_time, end_time, pickuploc, droploc, finalDays, finalHours);
        Carrcv.setAdapter(carAdapter);


        PickupLocation = findViewById(R.id.PickupLoc);
        DropLocation = findViewById(R.id.DropLoc);

        pickuploc = getIntent().getStringExtra("pickuplocation");
        droploc = getIntent().getStringExtra("droplocation");
        start_date = getIntent().getStringExtra("startdate");
        end_date = getIntent().getStringExtra("enddate");
        start_time = getIntent().getStringExtra("startingtime");
        end_time = getIntent().getStringExtra("endingtime");


        DropLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindCarActivity.this, BookingSummaryActivity.class);
                intent.putExtra("pickuplocation", pickuploc);
                intent.putExtra("droplocation", droploc);
                intent.putExtra("startdate", start_date);
                intent.putExtra("enddate", end_date);
                intent.putExtra("startingtime", start_time);
                intent.putExtra("endingtime", end_time);
                intent.putExtra("finalhours", finalHours);
                intent.putExtra("finaldays", finalDays);
                startActivity(intent);
            }
        });

        PickupLocation.setText(pickuploc.trim());
        DropLocation.setText(droploc.toString().trim());

        db = FirebaseFirestore.getInstance();
        db.collection("Cars").whereEqualTo("available_flag", "true").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            CarModel cm = new CarModel();
                            cm.setModel_name(String.valueOf(d.getString("model_name")));
                            cm.setBrand_name(String.valueOf(d.getString("brand_name")));
                            cm.setCategory_name(String.valueOf(d.getString("category_name")));
                            cm.setCost(String.valueOf(d.getString("cost")));
                            cm.setFuel(String.valueOf(d.getString("fuel")));
                            cm.setTransmission(String.valueOf(d.getString("transmission")));
                            cm.setAvailable_flag(String.valueOf(d.getString("available_flag")));
                            cm.setCapacity(String.valueOf(d.getString("capacity")));
                            cm.setCar_image(String.valueOf(d.getString("car_image")));
                            carList.add(cm);
                            carIds.put(String.valueOf(d.getString("model_name")), d.getId().toString());
                            noOfCars++;
                        }
                        carAdapter.notifyDataSetChanged();
                        carCount.setText(String.valueOf(noOfCars) + " Cars Available");
                    }
                });

    }

}