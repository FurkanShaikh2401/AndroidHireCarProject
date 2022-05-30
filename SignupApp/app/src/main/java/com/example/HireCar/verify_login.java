package com.example.HireCar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verify_login extends AppCompatActivity {


    private EditText inputOtp1, inputOtp2, inputOtp3, inputOtp4, inputOtp5, inputOtp6;
    TextView L_mobile, Resend_txt;
    Button verify_button;
    String Backotp;
    FirebaseAuth mAuth;


    String verifitionsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_otp);

        verify_button = findViewById(R.id.buttonVerify);
        Resend_txt =findViewById(R.id.textResendOTP);
        // get the reference of view for OTP
        inputOtp1 = findViewById(R.id.inputCode1);
        inputOtp2 = findViewById(R.id.inputCode2);
        inputOtp3 = findViewById(R.id.inputCode3);
        inputOtp4 = findViewById(R.id.inputCode4);
        inputOtp5 = findViewById(R.id.inputCode5);
        inputOtp6 = findViewById(R.id.inputCode6);

        L_mobile = findViewById(R.id.textMobile);

        String PhoneNumber = "+91"+getIntent().getStringExtra("phone_number");


        L_mobile.setText(String.format(
                "+91"+getIntent().getStringExtra("phone_number")
        ));
        mAuth=FirebaseAuth.getInstance();
        SendVerificationCode(PhoneNumber);
        setupOtpInputs();

        verify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String U_code=inputOtp1.getText().toString().trim()+inputOtp2.getText().toString().trim()+inputOtp3.getText().toString().trim()+
                        inputOtp4.getText().toString().trim()+inputOtp5.getText().toString().trim()+inputOtp6.getText().toString().trim();

                if(U_code.isEmpty()){
                    Toast.makeText(verify_login.this, "Enter the OTP Code", Toast.LENGTH_SHORT).show();
                }

                verifyCode(U_code);
            }
        });

        Resend_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_resend=new Intent(verify_login.this,verify_login.class);
                intent_resend.putExtra("phone_number",PhoneNumber);
                intent_resend.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent_resend);
            }
        });

    }

    private void verifyCode(String code){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verifitionsId,code);
        SignInWithCredential(credential);
    }

    private void SignInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(verify_login.this,homepage.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(verify_login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setupOtpInputs() {
        inputOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()) {
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
                if (!s.toString().trim().isEmpty()) {
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
                if (!s.toString().trim().isEmpty()) {
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
                if (!s.toString().trim().isEmpty()) {
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
                if (!s.toString().trim().isEmpty()) {
                    inputOtp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    private void SendVerificationCode(String Number)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                Number,
                60,
                TimeUnit.SECONDS,
                verify_login.this,
                mCallBack
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if(code!=null){
                verifyCode(code);
            }
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verifitionsId=s;
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(verify_login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}