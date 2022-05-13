package com.example.HireCar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FindCarActivity extends AppCompatActivity {

    String location_name, trip_dates, start_time, end_time;

    TextView setLocationTextView;

    //Recyclerview object and CarAdapter object
    RecyclerView Carrcv;
    CarAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_car);


        Carrcv = (RecyclerView) findViewById(R.id.recyclerView2);
        Carrcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CarAdapter(dataqueue(),getApplicationContext());
        Carrcv.setAdapter(adapter);


        setLocationTextView = findViewById(R.id.LocationText);

        setLocationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindCarActivity.this, BookingSummaryActivity.class));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            location_name = extras.getString("location");
            trip_dates = extras.getString("trip_dates");
            start_time = extras.getString("startingtime");
            end_time = extras.getString("endingtime");
            Toast.makeText(getApplicationContext(), "Location: " + location_name, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Dates: " + trip_dates, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Starting Time: " + start_time, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Ending Time: " + end_time, Toast.LENGTH_SHORT).show();
        }

        setLocationTextView.setText(location_name);
    }

    // Model For RecyclerView
    private ArrayList<Model> dataqueue() {
        ArrayList<Model> holder = new ArrayList<>();

        Model mobj1 = new Model();
        mobj1.setBrandTitle("Hyundai");
        mobj1.setModelTitle("Grand i10");
        mobj1.setdTitle1("diesel");
        mobj1.setdTitle2("manual");
        mobj1.setdTitle3("5 seat");
        mobj1.setPriceTag("$1,944");
        mobj1.setImgId(R.drawable.car1);
        mobj1.setBookBtn("Book");

        holder.add(mobj1);

        Model mobj2 = new Model();
        mobj2.setBrandTitle("Maruti");
        mobj2.setModelTitle("Suzuki");
        mobj2.setdTitle1("petrol");
        mobj2.setdTitle2("automatic");
        mobj2.setdTitle3("6 seat");
        mobj2.setPriceTag("$2,250");
        mobj2.setImgId(R.drawable.car1);
        mobj2.setBookBtn("Book");


        holder.add(mobj2);

        Model mobj3 = new Model();
        mobj3.setBrandTitle("Hyundai");
        mobj3.setModelTitle("Grand i10");
        mobj3.setdTitle1("diesel");
        mobj3.setdTitle2("manual");
        mobj3.setdTitle3("5 seat");
        mobj3.setPriceTag("$1,944");
        mobj3.setImgId(R.drawable.car1);
        mobj3.setBookBtn("Book");


        holder.add(mobj3);


        Model mobj4 = new Model();
        mobj4.setBrandTitle("Hyundai");
        mobj4.setModelTitle("Grand i10");
        mobj4.setdTitle1("petrol");
        mobj4.setdTitle2("automatic");
        mobj4.setdTitle3("5 seat");
        mobj4.setPriceTag("$1,944");
        mobj4.setImgId(R.drawable.car1);
        mobj4.setBookBtn("Book");


        holder.add(mobj4);

        Model mobj5 = new Model();
        mobj5.setBrandTitle("Hyundai");
        mobj5.setModelTitle("Grand i10");
        mobj5.setdTitle1("petrol");
        mobj5.setdTitle2("manual");
        mobj5.setdTitle3("7 seat");
        mobj5.setPriceTag("$1,944");
        mobj5.setImgId(R.drawable.car1);
        mobj5.setBookBtn("Book");

        holder.add(mobj5);

        Model mobj6 = new Model();
        mobj6.setBrandTitle("Hyundai");
        mobj6.setModelTitle("Grand i10");
        mobj6.setdTitle1("petrol");
        mobj6.setdTitle2("manual");
        mobj6.setdTitle3("5 seat");
        mobj6.setPriceTag("$1,944");
        mobj6.setImgId(R.drawable.car1);
        mobj6.setBookBtn("Book");

        holder.add(mobj6);


        Model mobj7 = new Model();
        mobj7.setBrandTitle("Hyundai");
        mobj7.setModelTitle("Grand i10");
        mobj7.setdTitle1("diesel");
        mobj7.setdTitle2("manual");
        mobj7.setdTitle3("7 seat");
        mobj7.setPriceTag("$1,944");
        mobj7.setImgId(R.drawable.car1);
        mobj7.setBookBtn("Book");


        holder.add(mobj7);

        Model mobj8 = new Model();
        mobj8.setBrandTitle("Hyundai");
        mobj8.setModelTitle("Grand i10");
        mobj8.setdTitle1("diesel");
        mobj8.setdTitle2("manual");
        mobj8.setdTitle3("5 seat");
        mobj8.setPriceTag("$1,944");
        mobj8.setImgId(R.drawable.car1);
        mobj8.setBookBtn("Book");

        holder.add(mobj8);

        Model mobj9 = new Model();
        mobj9.setBrandTitle("Hyundai");
        mobj9.setModelTitle("Grand i10");
        mobj9.setdTitle1("diesel");
        mobj9.setdTitle2("manual");
        mobj9.setdTitle3("6 seat");
        mobj9.setPriceTag("$1,944");
        mobj9.setImgId(R.drawable.car1);
        mobj9.setBookBtn("Book");

        holder.add(mobj9);


        Model mobj10 = new Model();
        mobj10.setBrandTitle("Hyundai");
        mobj10.setModelTitle("Grand i10");
        mobj10.setdTitle1("petrol");
        mobj10.setdTitle2("manual");
        mobj10.setdTitle3("5 seat");
        mobj10.setPriceTag("$1,944");
        mobj10.setImgId(R.drawable.car1);
        mobj10.setBookBtn("Book");


        holder.add(mobj10);

        return holder;
    }
}