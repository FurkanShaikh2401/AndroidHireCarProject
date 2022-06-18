package com.example.HireCar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MyBookingActivity extends AppCompatActivity {
    String start_date, end_date, start_time, end_time, brandName, modelName, amount, carid;

    FirebaseFirestore db;
    FirebaseStorage fs;
    StorageReference listRef;

    FirebaseAuth mAuth;

    String bokid, carImage, bodyType, FinalImage;

    BookingHistoryAdapter bookingHistoryAdapter;
    RecyclerView bookrcv;

    ArrayList<String> amountsList;
    ArrayList<String> imagelist;
    ArrayList<BookingHistoryModel> bookList;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyBookingActivity.this, ProfileSettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooking);

        bookrcv = findViewById(R.id.myrecyclerview2);
        bookrcv.setLayoutManager(new LinearLayoutManager(this));


        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();
        fs = FirebaseStorage.getInstance();
        listRef = fs.getReference().child("CarImages");

        bookList = new ArrayList<>();
        bookingHistoryAdapter = new BookingHistoryAdapter(bookList, getApplicationContext());
        bookrcv.setAdapter(bookingHistoryAdapter);



        Intent intent = getIntent();
        start_date =intent.getStringExtra("startDate");
        end_date =intent.getStringExtra("endDate");
        start_time =intent.getStringExtra("startTime");
        end_time =intent.getStringExtra("endTime");
        amount =intent.getStringExtra("price");
        modelName =intent.getStringExtra("modelName");
        carid =intent.getStringExtra("carid");


//
//        Toast.makeText(getApplicationContext(), "StartDate: " + start_date, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "EndDate: " + end_date, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "StartTime: " + start_time, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "EndTime: " + end_time, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Amount: " + amount, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "ModelName: " + modelName, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "CarId: " + carid, Toast.LENGTH_SHORT).show();


//        Toast.makeText(getApplicationContext(), "Model: " +intent.getStringExtra("modelName"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "ImageLink" +intent.getStringExtra("carImage"), Toast.LENGTH_SHORT).show();


        db.collection("Booking").orderBy("end_date").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            if(d.getString("user_id").equals(mAuth.getCurrentUser().getUid().toString())) {
                                BookingHistoryModel bhm = new BookingHistoryModel();
                                bhm.setModelTitle(d.getString("model_name"));
                                bhm.setStartDate(d.getString("start_date"));
                                bhm.setEndDate(d.getString("end_date"));
                                bhm.setStartTime(d.getString("start_time"));
                                bhm.setEndTime(d.getString("end_time"));
                                bhm.setPriceTag(d.getString("amount"));
                                bhm.setImage(String.valueOf(d.getString("car_image")));
                                bookList.add(bhm);
                            }
                        }
                        bookingHistoryAdapter.notifyDataSetChanged();

                    }
                });


    }

}
