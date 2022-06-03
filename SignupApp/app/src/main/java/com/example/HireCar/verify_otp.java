package com.example.HireCar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.HireCar.DatabaseFiles.DBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class verify_otp extends AppCompatActivity {

    private EditText inputOtp1, inputOtp2, inputOtp3, inputOtp4, inputOtp5, inputOtp6;
    TextView L_mobile,Resend_txt;
    Button verify_button;
    String Backotp;
    FirebaseAuth mAuth;
    DBHelper dbHelper;
    Bitmap bitmap2;

    // pick data for per. activity variables.
    String L_email,L_name,L_dlnumber,CarUri;
    String New_verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.verify_otp);

        verify_button = findViewById(R.id.buttonVerify);
        dbHelper = new DBHelper(verify_otp.this);


        // get the reference of view for OTP
        inputOtp1 = findViewById(R.id.inputCode1);
        inputOtp2 = findViewById(R.id.inputCode2);
        inputOtp3 = findViewById(R.id.inputCode3);
        inputOtp4 = findViewById(R.id.inputCode4);
        inputOtp5 = findViewById(R.id.inputCode5);
        inputOtp6 = findViewById(R.id.inputCode6);

        


        L_mobile = findViewById(R.id.textMobile);
        L_mobile.setText(String.format(
                "+91"+getIntent().getStringExtra("mobile")
        ));


        //pre. data
        L_email = getIntent().getStringExtra("email");
        L_name = getIntent().getStringExtra("fname");
        L_dlnumber = getIntent().getStringExtra("dl_number");
        CarUri = getIntent().getStringExtra("image_uri");
        Backotp = getIntent().getStringExtra("otp_mgs");
        // To set the status bar and navigation bar color to colorBackPrimary
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            Window window = this.getWindow();
//            window.setStatusBarColor(this.getResources().getColor(R.color.colorSecondaryPink));
//            window.setNavigationBarColor(this.getResources().getColor(R.color.colorSecondaryPink));
//        }


        TextView textMobile = findViewById(R.id.textMobile);
        textMobile.setText(String.format(
                "+91"+ getIntent().getStringExtra("mobile")
        ));
            setupOtpInputs();
            verify_for_register();
            setupOtpInputs();
            resend_for_register();
        

        


        

     }

     private void resend_for_register(){
         Resend_txt=findViewById(R.id.textResendOTP);
         Resend_txt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 PhoneAuthProvider.getInstance().verifyPhoneNumber(
                         "+91"+getIntent().getStringExtra("mobile"),
                         60,
                         TimeUnit.SECONDS,
                         verify_otp.this,
                         new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                             @Override
                             public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                             }

                             @Override
                             public void onVerificationFailed(@NonNull FirebaseException e) {
                                 Toast.makeText(verify_otp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                             }

                             @Override
                             public void onCodeSent(@NonNull String s1, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                 Backotp=s1;
                                 Toast.makeText(verify_otp.this, "OTP resend succusfully", Toast.LENGTH_SHORT).show();

                             }
                         }
                 );
             }
         });
     }
     private void verify_for_register(){
         verify_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if(!inputOtp1.getText().toString().trim().isEmpty() && !inputOtp2.getText().toString().trim().isEmpty()&& !inputOtp3.getText().toString().trim().isEmpty()&&
                         !inputOtp4.getText().toString().trim().isEmpty()&& !inputOtp5.getText().toString().trim().isEmpty()&& !inputOtp6.getText().toString().trim().isEmpty()){
                     String enterOtp =inputOtp1.getText().toString()+inputOtp2.getText().toString()+inputOtp3.getText().toString()+
                             inputOtp4.getText().toString()+inputOtp5.getText().toString()+inputOtp6.getText().toString();

                     if(Backotp!=null){
                         PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(Backotp,enterOtp);
                         FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
//                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        if(task.isSuccessful()){
//
//                                            Toast.makeText(verify_otp.this, "Successful", Toast.LENGTH_SHORT).show();
////                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
////                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                                            startActivity(intent);
//                                        }
//                                        else{
//                                            Toast.makeText(verify_otp.this, "Enter the correct OTP..!", Toast.LENGTH_SHORT).show();
//                                        }
//
//                                    }
//                                })
                                 .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                     @Override
                                     public void onSuccess(AuthResult authResult) {
                                         setUser(authResult);
//                                         storeRegisterDataSqlite();
                                         Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                         startActivity(intent);
                                     }
                                 });

                     }
                     else{
                         Toast.makeText(verify_otp.this, "Please check internet connection!!", Toast.LENGTH_SHORT).show();
                     }
                 }
                 else{
                     Toast.makeText(verify_otp.this, "Please Enter all number", Toast.LENGTH_SHORT).show();
                 }

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

    private void setUser(AuthResult authResult) {
        L_email = getIntent().getStringExtra("email");
        L_name = getIntent().getStringExtra("fname");
        L_dlnumber = getIntent().getStringExtra("dl_number");
        CarUri = getIntent().getStringExtra("image_uri");
        String L_mobile1;
        L_mobile1= getIntent().getStringExtra("mobile");

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        Map<String, String> data = new HashMap<>();
        data.put("email", L_email);
        data.put("full name", L_name);
        data.put("moblie", "+91"+L_mobile1);
        data.put("is_admin","false");
        data.put("DL number", "GJ01" + L_dlnumber);

        data.put("DL_photo", CarUri);
        firebaseFirestore.collection("users").document(authResult.getUser().getUid()).set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(verify_otp.this, "Account Created SuccessFull: Please VerifyEmail", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    //    Login methods
//
//    private void storeRegisterDataSqlite() {
//        String name = getIntent().getStringExtra("fname");
//        String email = getIntent().getStringExtra("email");
//        String phoneno = getIntent().getStringExtra("mobile");
//        String dlnumber = getIntent().getStringExtra("dl_number");
//        byte imgbyte[]= getIntent().getByteArrayExtra("image_bit");
//
//
//        boolean result = dbHelper.InsertUserData(name, email, phoneno, dlnumber, imgbyte, false);
//
//        if( result == true){
//            Toast.makeText(verify_otp.this, "User Data Saved Successfully", Toast.LENGTH_LONG).show();
//            String nameDB = dbHelper.getUserName(name);
//            Toast.makeText(verify_otp.this, "UserName: "+ nameDB, Toast.LENGTH_LONG).show();
//
//        }
//        else{
//            Toast.makeText(verify_otp.this, "Failed to Save User Data", Toast.LENGTH_LONG).show();
//        }
//
//    }
    
}
