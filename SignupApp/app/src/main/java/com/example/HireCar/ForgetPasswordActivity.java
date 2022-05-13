package com.example.HireCar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {

    Button continue_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        // initialize and add onClickListener to continute button
        continue_btn = findViewById(R.id.fp_c_button);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPasswordActivity.this, ForgetPasswordOTPActivity.class);
                startActivity(intent);
            }
        });
    }
}
