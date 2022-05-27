package com.example.HireCar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookingSummaryActivity extends AppCompatActivity {

    ImageView back_to_findcar_btn;
    Button payment_btn ;
    TextView start_date, end_date, start_time, end_time, pickuploc, droploc;
    String start_date1, end_date1, start_time1, end_time1, pickuploc1, droploc1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);

        start_time = findViewById(R.id.starttime_txt);
        end_time = findViewById(R.id.endtime_txt);
        start_date = findViewById(R.id.startdate_txt);
        end_date = findViewById(R.id.enddate_txt);
        pickuploc = findViewById(R.id.PickupLoc);
        droploc = findViewById(R.id.DropLoc);


        Toast.makeText(getApplicationContext(), "brandName: " +
                getIntent().getStringExtra("brandName"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "modelName: " +
                getIntent().getStringExtra("modelName"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "FuelName: " +
                getIntent().getStringExtra("FuelTitle"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "TransmissionMode: " +
                getIntent().getStringExtra("TransmissionTitle"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "Capcity: " +
                getIntent().getStringExtra("CapacityTitle"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "Price: " +
                getIntent().getStringExtra("PriceTitle"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "Pickup: " +
                getIntent().getStringExtra("PickUpLoc"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "Drop: " +
                getIntent().getStringExtra("DropLoc"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "StartDate: " +
                getIntent().getStringExtra("Start_Date"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "EndDate: " +
                getIntent().getStringExtra("End_Date"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "StartTime: " +
                getIntent().getStringExtra("Start_Time"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "EndTime: " +
                getIntent().getStringExtra("End_Time"), Toast.LENGTH_SHORT).show();

//        Intent intent = getIntent();
//        pickuploc.setText(intent.getExtras().getString("PickUpLoc"));
//        Toast.makeText(getApplicationContext(), "Profile: " +
//                    getIntent().getIntExtra("CarImage",0), Toast.LENGTH_SHORT).show();


        // initializing and setting the onClickListener
        back_to_findcar_btn = findViewById(R.id.bs_back_btn);
        back_to_findcar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingSummaryActivity.this, FindCarActivity.class));
            }
        });

        // initializing and setting the onClickListener
        payment_btn = findViewById(R.id.payment_btn);
        payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingSummaryActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

}