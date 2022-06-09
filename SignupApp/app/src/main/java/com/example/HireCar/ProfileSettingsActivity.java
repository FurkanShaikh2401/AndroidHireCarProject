package com.example.HireCar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileSettingsActivity extends AppCompatActivity {

    ImageView ProfileManageBtn, MyBookingBtn, faqsBtn, policyBtn, cpBtn, LogoutBtn;

    TextView UserName,UserEmail,UserPhone;

    FirebaseAuth mAuth;
//    String userE,userp,userN;

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

        UserName=findViewById(R.id.userName);
        UserEmail=findViewById(R.id.userEmail);
        UserPhone=findViewById(R.id.userPhone);

        mAuth=FirebaseAuth.getInstance();
////        userE= mAuth.getCurrentUser().getEmail();
//        userp= mAuth.getCurrentUser().getPhoneNumber();
////        userN= mAuth.getCurrentUser().getDisplayName();
        userDetails();
//        Toast.makeText(this, "User phone : "+userp, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "User Email : "+userE, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "User Name : "+userN, Toast.LENGTH_SHORT).show();


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
                intent_signout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_signout);
            }
        });

    }

    public void userDetails() {
        final String[] is_admin = new String[2];
        FirebaseFirestore rootref = FirebaseFirestore.getInstance();
        CollectionReference applref = rootref.collection("users");
        DocumentReference appl_id_ref = applref.document(mAuth.getCurrentUser().getUid().toString());

        appl_id_ref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot.exists()) {
                    is_admin[0] = (String) documentSnapshot.get("email");
                    is_admin[1] = (String) documentSnapshot.get("full name");
//                    Toast.makeText(this, is_admin[0].toString().trim(), Toast.LENGTH_SHORT).show();
                    UserEmail.setText(is_admin[0].trim());
                    UserName.setText(is_admin[1].trim());
                    UserPhone.setText(mAuth.getCurrentUser().getPhoneNumber());



                }
            }

        });
    }
}
