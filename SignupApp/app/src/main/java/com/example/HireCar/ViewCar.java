package com.example.HireCar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewCar extends AppCompatActivity {

    Button add_car;
    FirebaseFirestore db;
    ArrayList<CarModelAdmin> CarList;
    RecyclerView AdminCarRcv;
    CarAdapterAdmin carAdapterAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_car);
        add_car = findViewById(R.id.add_car);

        AdminCarRcv=findViewById(R.id.admincarrcv);
        AdminCarRcv.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        CarList = new ArrayList<>();
        carAdapterAdmin =  new CarAdapterAdmin(CarList,  getApplicationContext());
        AdminCarRcv.setAdapter(carAdapterAdmin);

        db.collection("Cars").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            CarModelAdmin carModelAdmin = new CarModelAdmin();
                            carModelAdmin.setCarId(d.getId());
                            carModelAdmin.setRegistration_number(d.getString("registration_number"));
                            carModelAdmin.setBrand_name(d.getString("brand_name"));
                            carModelAdmin.setModel_name(d.getString("model_name"));
                            carModelAdmin.setFuel(d.getString("fuel"));
                            carModelAdmin.setTransmission(d.getString("transmission"));
                            carModelAdmin.setCapacity(d.getString("capacity"));
                            carModelAdmin.setCar_image(d.getString("car_image"));
                            carModelAdmin.setCost(d.getString("cost"));
                            carModelAdmin.setCategory_name(d.getString("category_name"));
                            carModelAdmin.setAvailable_flag(d.getString("available_flag"));
                            CarList.add(carModelAdmin);
                        }
                        carAdapterAdmin.notifyDataSetChanged();
                    }
                });



        add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ViewCar.this,Add_Car.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });



    }
}