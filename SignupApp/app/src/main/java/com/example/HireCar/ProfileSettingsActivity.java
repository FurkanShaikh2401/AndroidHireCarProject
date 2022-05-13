package com.example.HireCar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileSettingsActivity extends AppCompatActivity {

    ImageView ProfileManageBtn, MyBookingBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        ProfileManageBtn = findViewById(R.id.navigate_profile);
        MyBookingBtn = findViewById(R.id.navigate_mybooking);

        // setting profile manage
        ProfileManageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileSettingsActivity.this, EditProfileActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // setting my booking activity
        MyBookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ProfileSettingsActivity.this, MyBookingActivity.class);
                intent2.setFlags(intent2.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
        });
    }
}
