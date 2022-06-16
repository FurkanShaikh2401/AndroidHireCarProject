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

    TextView PickupLocation, DropLocation;
    LinearLayout headerLayout;

    //Recyclerview object and CarAdapter object
    RecyclerView Carrcv;
    CarAdapter carAdapter;
    FirebaseFirestore db;
    ArrayList<CarModel> carList;
    HashMap<String, String> carIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_car);

        pickuploc =  getIntent().getStringExtra("pickuplocation");
        droploc =  getIntent().getStringExtra("droplocation");
        start_date =  getIntent().getStringExtra("startdate");
        end_date =  getIntent().getStringExtra("enddate");
        start_time = getIntent().getStringExtra("startingtime");
        end_time =  getIntent().getStringExtra("endingtime");
        finalDays = getIntent().getStringExtra("finaldays");
        finalHours = getIntent().getStringExtra("finalhours");

        db = FirebaseFirestore.getInstance();
        carList = new ArrayList<>();
        carIds = new HashMap<>();

        Carrcv = (RecyclerView) findViewById(R.id.recyclerView2);
        Carrcv.setLayoutManager(new LinearLayoutManager(this));

      //  dataqueue();
        carAdapter = new CarAdapter(carList, carIds, getApplicationContext(), start_date, end_date, start_time, end_time, pickuploc, droploc, finalDays, finalHours);
        Carrcv.setAdapter(carAdapter);


        PickupLocation = findViewById(R.id.PickupLoc);
        DropLocation = findViewById(R.id.DropLoc);

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {x
            pickuploc =  getIntent().getStringExtra("pickuplocation");
            droploc =  getIntent().getStringExtra("droplocation");
            start_date =  getIntent().getStringExtra("startdate");
            end_date =  getIntent().getStringExtra("enddate");
            start_time = getIntent().getStringExtra("startingtime");
            end_time =  getIntent().getStringExtra("endingtime");
            Toast.makeText(getApplicationContext(), "PickupLocation: " + pickuploc, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "DropLocation: " + droploc, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "StartDate: " + start_date, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "EndDate: " + end_date, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Starting Time: " + start_time, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Ending Time: " + end_time, Toast.LENGTH_SHORT).show();
//        }


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
                        for(DocumentSnapshot d:list){
                            CarModel cm = new CarModel();
                            cm.setModel_name(String.valueOf(d.getString("model_name")));
                            cm.setBrand_name(String.valueOf(d.getString("brand_name")));
//                            cm.setCar_id(String.valueOf(d.getString("car_id")));
                            cm.setCategory_name(String.valueOf(d.getString("category_name")));
                            cm.setCost(String.valueOf(d.getString("cost")));
                            cm.setFuel(String.valueOf(d.getString("fuel")));
                            cm.setTransmission(String.valueOf(d.getString("transmission")));
                            cm.setAvailable_flag(String.valueOf(d.getString("available_flag")));
                            cm.setCapacity(String.valueOf(d.getString("capacity")));
                            cm.setCar_image(String.valueOf(d.getString("car_image")));
                            carList.add(cm);
                            carIds.put(String.valueOf(d.getString("model_name")), d.getId().toString());
                        }
                        carAdapter.notifyDataSetChanged();
                    }
                });

//        setLocationTextView.setText(location_name);
    }

    // Model For RecyclerView
//    private void dataqueue() {
//        db.collection("Cars").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                        for (DocumentSnapshot d : list) {
////                            CarModel cm = d.toObject(CarModel.class);
//                            CarModel cm = new CarModel();
//                            cm.setModel_name(String.valueOf(d.getString("model_name")));
//                            cm.setBrand_name(String.valueOf(d.getString("brand_name")));
////                            cm.setCar_id(String.valueOf(d.getString("car_id")));
//                            cm.setCategory_name(String.valueOf(d.getString("category_name")));
//                            cm.setCost(String.valueOf(d.getString("cost")));
//                            cm.setFuel(String.valueOf(d.getString("fuel")));
//                            cm.setTransmission(String.valueOf(d.getString("transmission")));
////                            cm.setAvailable_flag(String.valueOf(d.getString("available_flag")));
//                            cm.setCapacity(String.valueOf(d.getString("capacity")));
//                            cm.setImageView2(String.valueOf(d.getString("car_image")));
//                            carList.add(cm);
//                        }
//                        carAdapter.notifyDataSetChanged();
//                    }
//                });
//    }
}