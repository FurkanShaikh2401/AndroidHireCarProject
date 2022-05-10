package com.example.signupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class BookingSummaryActivity extends AppCompatActivity {

    ImageView back_to_findcar_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);

        // initializing and setting the onClickListener
        back_to_findcar_btn = findViewById(R.id.bs_back_btn);
        back_to_findcar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingSummaryActivity.this, FindCarActivity.class));
            }
        });

    }

}