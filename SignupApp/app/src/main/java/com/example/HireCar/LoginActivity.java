package com.example.HireCar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;


public class LoginActivity extends AppCompatActivity {

    Button gotosignup_button, login_btn;
    TextInputEditText L_mobile;
    private FirebaseAuth mAuth;

    public String admin_not;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();


        if (mAuth.getCurrentUser() != null) {

            usercheck();

        }
        setContentView(R.layout.activity_login);
        initUi_Login();
        check_text();


        // signup screen calling
        gotosignup_button = findViewById(R.id.gotosu_button);
        gotosignup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                intent3.setFlags(intent3.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
            }
        });


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
                    Toast.makeText(this, is_admin[0].toString().trim(), Toast.LENGTH_SHORT).show();
                    admin_not = is_admin[0].toString().trim();

                    if (admin_not.equals("0")) {
                        Intent intent4 = new Intent(getApplicationContext(), homepage.class);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent4);
                    } else if (admin_not.equals("1")) {
                        Intent intent5 = new Intent(getApplicationContext(), welcome_screen.class);
                        intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent5);
                    }
                }
            }

        });


    }

    private void initUi_Login() {
        L_mobile = findViewById(R.id.L_mobile);
        login_btn = findViewById(R.id.login_button);
        gotosignup_button = findViewById(R.id.gotosu_button);


    }

    private void check_text() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (L_mobile.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Phone number Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent_verify = new Intent(getApplicationContext(), verify_login.class);
                intent_verify.putExtra("phone_number", L_mobile.getText().toString());
                startActivity(intent_verify);
            }
        });

    }
}