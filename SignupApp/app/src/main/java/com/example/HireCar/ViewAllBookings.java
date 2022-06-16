package com.example.HireCar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ViewAllBookings extends AppCompatActivity {

    FirebaseFirestore db;
    ArrayList<CarBookModelAdmin> bookList;

    CarBookAdapterAdmin carBookAdapterAdmin;
    RecyclerView bookrcv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_bookings);

        bookrcv = findViewById(R.id.bookingrecyclerview2);
        bookrcv.setLayoutManager(new LinearLayoutManager(this));

        bookList = new ArrayList<>();
        CarBookAdapterAdmin carBookAdapterAdmin= new CarBookAdapterAdmin(bookList, getApplicationContext());
        bookrcv.setAdapter(carBookAdapterAdmin);

        db = FirebaseFirestore.getInstance();

        db.collection("Booking").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            String no11;
                                CarBookModelAdmin carBookModelAdmin = new CarBookModelAdmin();
                                carBookModelAdmin.setCar_id(d.getId());
                                carBookModelAdmin.setModel_name(d.getString("model_name"));
                                carBookModelAdmin.setPickup_location(d.getString("pickup_location"));
                                carBookModelAdmin.setDrop_location(d.getString("drop_location"));
                                carBookModelAdmin.setStart_date(d.getString("start_date"));
                                carBookModelAdmin.setEnd_date(d.getString("end_date"));
                                carBookModelAdmin.setStart_time(d.getString("start_time"));
                                carBookModelAdmin.setEnd_time(d.getString("end_time"));
                                carBookModelAdmin.setAmount(d.getString("amount"));
                                carBookModelAdmin.setUser_id(d.getString("user_id"));
                                carBookModelAdmin.setMobileno(d.getString("mobile"));
                                carBookModelAdmin.setCar_image(String.valueOf(d.getString("car_image")));
                                bookList.add(carBookModelAdmin);
                        }
                        carBookAdapterAdmin.notifyDataSetChanged();
                    }
                });

    }

}