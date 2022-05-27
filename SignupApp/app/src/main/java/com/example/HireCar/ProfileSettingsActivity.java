package com.example.HireCar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileSettingsActivity extends AppCompatActivity {

    ImageView ProfileManageBtn, MyBookingBtn, faqsBtn, policyBtn, cpBtn, LogoutBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        ProfileManageBtn = findViewById(R.id.navigate_profile);
        MyBookingBtn = findViewById(R.id.navigate_mybooking);
        faqsBtn = findViewById(R.id.navigate_faq);
        policyBtn = findViewById(R.id.navigate_policy);
        cpBtn = findViewById(R.id.navigate_cp);
        LogoutBtn = findViewById(R.id.navigate_logout);

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

        // setting faqs activity
        faqsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(ProfileSettingsActivity.this, faqs.class);
                intent3.setFlags(intent3.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
            }
        });

        // setting change password activity
        cpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(ProfileSettingsActivity.this, change_password.class);
                intent5.setFlags(intent5.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent5);
            }
        });

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth;
                mAuth=FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intent_signout=new Intent(ProfileSettingsActivity.this,LoginActivity.class);
                intent_signout.setFlags(intent_signout.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_signout);
            }
        });

    }
}
