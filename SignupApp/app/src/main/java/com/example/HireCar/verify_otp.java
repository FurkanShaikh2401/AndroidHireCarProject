package com.example.HireCar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class verify_otp extends AppCompatActivity {

    private EditText inputOtp1, inputOtp2, inputOtp3, inputOtp4, inputOtp5, inputOtp6;
    Button verify_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.verify_otp);

        verify_button = findViewById(R.id.buttonVerify);

        // To set the status bar and navigation bar color to colorBackPrimary
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            Window window = this.getWindow();
//            window.setStatusBarColor(this.getResources().getColor(R.color.colorSecondaryPink));
//            window.setNavigationBarColor(this.getResources().getColor(R.color.colorSecondaryPink));
//        }


        TextView textMobile = findViewById(R.id.textMobile);
        textMobile.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        // get the reference of view for OTP
        inputOtp1 = findViewById(R.id.inputCode1);
        inputOtp2 = findViewById(R.id.inputCode2);
        inputOtp3 = findViewById(R.id.inputCode3);
        inputOtp4 = findViewById(R.id.inputCode4);
        inputOtp5 = findViewById(R.id.inputCode5);
        inputOtp6 = findViewById(R.id.inputCode6);

        setupOtpInputs();

        verify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

     }

     private void setupOtpInputs(){
        inputOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().isEmpty()){
                    inputOtp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (inputOtp1.getText().toString().length() == 1) {
                    inputOtp2.requestFocus();
                }
            }
        });
        inputOtp2.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
             }

             @Override
             public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                 if(!s.toString().trim().isEmpty()){
                     inputOtp3.requestFocus();
                 }
             }

             @Override
             public void afterTextChanged(Editable editable) {
                 if (inputOtp2.getText().toString().length() == 1) {
                     inputOtp3.requestFocus();
                 }
             }
         });
        inputOtp3.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
             }

             @Override
             public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                 if(!s.toString().trim().isEmpty()){
                     inputOtp4.requestFocus();
                 }
             }

             @Override
             public void afterTextChanged(Editable editable) {
                 if (inputOtp3.getText().toString().length() == 1) {
                     inputOtp4.requestFocus();
                 }
             }
         });
        inputOtp4.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
             }

             @Override
             public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                 if(!s.toString().trim().isEmpty()){
                     inputOtp5.requestFocus();
                 }
             }

             @Override
             public void afterTextChanged(Editable editable) {
                 if (inputOtp4.getText().toString().length() == 1) {
                     inputOtp5.requestFocus();
                 }
             }
         });
        inputOtp5.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
             }

             @Override
             public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                 if(!s.toString().trim().isEmpty()){
                     inputOtp6.requestFocus();
                 }
             }

             @Override
             public void afterTextChanged(Editable editable) {
             }
         });

     }


}
