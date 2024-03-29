package com.example.HireCar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class welcome_screen extends AppCompatActivity {
    FirebaseAuth mAuth;
    public String admin_not;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_screen);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {

            usercheck();

        } else {
            Intent intent5 = new Intent(getApplicationContext(), LoginActivity.class);
            intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent5);
        }
    }

    public void usercheck() {
        final String[] is_admin = new String[1];
        FirebaseFirestore rootref = FirebaseFirestore.getInstance();
        CollectionReference applref = rootref.collection("users");
        DocumentReference appl_id_ref = applref.document(mAuth.getCurrentUser().getUid().toString());

        appl_id_ref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot.exists()) {
                    is_admin[0] = (String) documentSnapshot.get("is_admin");
                    admin_not = is_admin[0].toString().trim();

                    if (admin_not.equals("0")) {
                        Intent intent4 = new Intent(getApplicationContext(), homepage.class);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent4);
                    } else if (admin_not.equals("1")) {
                        Intent intent5 = new Intent(getApplicationContext(), admin_home.class);
                        intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent5);
                    }
                }
            }

        });
    }

}