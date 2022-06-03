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

    Button gotosignup_button, forget_pwd_btn, login_btn;
    TextInputEditText L_email,L_password,L_mobile;
    private FirebaseAuth mAuth;
    String New_verificationId;

    public String admin_not;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth=FirebaseAuth.getInstance();



        if(mAuth.getCurrentUser()!=null){

            usercheck();

        }
        setContentView(R.layout.activity_login);
        initUi_Login();
        check_text();

//        login_btn = findViewById(R.id.login_button);

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

        // forget password screen calling
//        forget_pwd_btn = findViewById(R.id.fp_button);
//        forget_pwd_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
//                startActivity(intent);
//            }
//        });

        //homepage calling
//        login_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, homepage.class);
//                startActivity(intent);
//            }
//        });
    }

    public void usercheck(){
        final String[] is_admin = new String[1];
        FirebaseFirestore rootref=FirebaseFirestore.getInstance();
        CollectionReference applref=rootref.collection("users");
        DocumentReference appl_id_ref=applref.document(mAuth.getCurrentUser().getUid().toString());

        appl_id_ref.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot documentSnapshot=task.getResult();
                if(documentSnapshot.exists()){
                    is_admin[0] = (String) documentSnapshot.get("is_admin");
                    Toast.makeText(this, is_admin[0].toString().trim(), Toast.LENGTH_SHORT).show();
                    admin_not=is_admin[0].toString().trim();

                    if(admin_not.equals("0")){
                        Intent intent4 = new Intent(getApplicationContext(),homepage.class);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent4);
                    }else if(admin_not.equals("1")){
                        Intent intent5 = new Intent(getApplicationContext(),welcome_screen.class);
                        intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent5);
                    }
                }
            }

        });

//        if(admin_not=="true"){
//            Toast.makeText(this, admin_not.toString(), Toast.LENGTH_SHORT).show();
//
//            Intent intent1 = new Intent(getApplicationContext(),homepage.class);
//            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent1);
//        }else {
//            Intent intent2 = new Intent(getApplicationContext(),welcome_screen.class);
//            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent2);
//        }


    }

    private void initUi_Login(){
//        L_email=findViewById(R.id.L_email);
//        L_password=findViewById(R.id.L_password);
        L_mobile=findViewById(R.id.L_mobile);

        login_btn = findViewById(R.id.login_button);
//        forget_pwd_btn = findViewById(R.id.fp_button);
        gotosignup_button = findViewById(R.id.gotosu_button);


    }

    private void check_text(){
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(L_email.getText().toString().isEmpty()){
//                    Toast.makeText(LoginActivity.this, "Email Required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (L_password.getText().toString().isEmpty())
//                {
//                    Toast.makeText(LoginActivity.this, "Password Required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                SignInwithEmail();

                if(L_mobile.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Phone number Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent_verify = new Intent(getApplicationContext(),verify_login.class);
                intent_verify.putExtra("phone_number",L_mobile.getText().toString());
//                intent_verify.putExtra("identify_data2","2");
                startActivity(intent_verify);
//                SignInwithNumber(L_mobile.getText().toString());
            }
        });

    }
//    private void SignInwithEmail(){
//        FirebaseAuth mAuth;
//        mAuth=FirebaseAuth.getInstance();
//        mAuth.signInWithEmailAndPassword(L_email.getText().toString(),L_password.getText().toString())
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Intent intent_login = new Intent(getApplicationContext(), MainActivity.class);
//                    intent_login.setFlags(intent_login.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent_login);
//                }
//                else {
//                    Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void SignInwithNumber(String number){
//
//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(mAuth)
//                        .setPhoneNumber("+91"+number)       // Phone number to verify
//                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                        .setActivity(this)                 // Activity (for callback binding)
//                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//    }
//private PhoneAuthProvider.OnVerificationStateChangedCallbacks
//    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//        @Override
//        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
//            final String code =credential.getSmsCode();
//            if(code!=null){
//                verifycode(code);
//            }
//        }
//
//        @Override
//        public void onVerificationFailed(@NonNull FirebaseException e) {
//
//            Toast.makeText(LoginActivity.this, "VerificationFailed check the mobile number", Toast.LENGTH_SHORT).show();
//            // Show a message and update the UI
//        }
//
//        @Override
//        public void onCodeSent(@NonNull String verificationId,
//                @NonNull PhoneAuthProvider.ForceResendingToken token) {
//            super.onCodeSent(verificationId,token);
//            New_verificationId=verificationId;
//        }
//    };
//
//
//    private void verifycode(String code){
//        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(New_verificationId,code);
//        signinbyCredential(credential);
//    }
//
//    private void signinbyCredential(PhoneAuthCredential credential) {
//
//        FirebaseAuth mAuth1=FirebaseAuth.getInstance();
//        mAuth1.signInWithCredential(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                Toast.makeText(LoginActivity.this, "Successfully ", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}