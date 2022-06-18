package com.example.HireCar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class admin_home extends AppCompatActivity {

    TextView AdminName,AdminEmail;
    FirebaseAuth mAuth;

    ImageView viewUsers,viewLocatins,viewCar, viewBookings,viewLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        AdminName=findViewById(R.id.adminName);
        AdminEmail =findViewById(R.id.adminEmail);

        viewCar = findViewById(R.id.navigate_cars);
        viewUsers = findViewById(R.id.navigate_users);
        viewLocatins =findViewById(R.id.navigate_locations);
        viewBookings = findViewById(R.id.navigate_bookings);
        viewLogout =findViewById(R.id.navigate_logout);
        AdminDetails();

        viewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth;
                mAuth=FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intent_signout=new Intent(admin_home.this,LoginActivity.class);
                intent_signout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_signout);
            }
        });

        viewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_home.this, ViewUsers.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        viewLocatins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(admin_home.this,ViewLocations.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });

        viewCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(admin_home.this,ViewCar.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });

        viewBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_home.this, ViewAllBookings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void AdminDetails() {
        mAuth=FirebaseAuth.getInstance();
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
                    AdminName.setText(is_admin[0].trim());
                    AdminEmail.setText(is_admin[1].trim());
//                    UserName.setText(is_admin[1].trim());
//                    UserPhone.setText(mAuth.getCurrentUser().getPhoneNumber());



                }
            }

        });
    }
}