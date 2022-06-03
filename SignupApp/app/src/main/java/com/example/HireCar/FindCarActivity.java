package com.example.HireCar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FindCarActivity extends AppCompatActivity {

    String pickuploc, droploc, start_date, end_date, start_time, end_time;

    TextView PickupLocation, DropLocation;
    LinearLayout headerLayout;

    //Recyclerview object and CarAdapter object
    RecyclerView Carrcv;
    CarAdapter adapter;


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

        Carrcv = (RecyclerView) findViewById(R.id.recyclerView2);
        Carrcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CarAdapter(dataqueue(),getApplicationContext(), start_date, end_date, start_time, end_time, pickuploc, droploc);
        Carrcv.setAdapter(adapter);


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
                startActivity(intent);
            }
        });

        PickupLocation.setText(pickuploc.trim());
        DropLocation.setText(droploc.toString().trim());

//        setLocationTextView.setText(location_name);
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