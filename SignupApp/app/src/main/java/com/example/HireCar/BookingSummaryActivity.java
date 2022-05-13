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
    TextView trip_dates, trip_start_time, trip_end_time, trip_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);

        trip_dates = findViewById(R.id.bs_trip_dates);
        trip_start_time = findViewById(R.id.bs_start_time);
        trip_end_time = findViewById(R.id.bs_end_time);
        trip_location = findViewById(R.id.bs_trip_location);

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