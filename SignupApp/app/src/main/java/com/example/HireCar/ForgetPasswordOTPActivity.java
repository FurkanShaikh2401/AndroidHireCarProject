package com.example.HireCar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordOTPActivity extends AppCompatActivity {

    Button otp_continue_btn, edit_email_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_otp);

        // initialize and add onClickListener to continute button
        otp_continue_btn = findViewById(R.id.fp_verify_otp_btn);
        edit_email_btn = findViewById(R.id.editemail_button);


        // redirect to homepage if otp is correct
        otp_continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPasswordOTPActivity.this, homepage.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // redirect to emailinput page if user wants to edit email id
        edit_email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPasswordOTPActivity.this, ForgetPasswordActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
