package com.example.HireCar;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.contract.ActivityResultContracts;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity{

    ImageView imageView;
    int code = 1;
    Uri image;
    Button Register_btn,selectimgbutton,Login_btn;
    TextInputEditText L_email,L_name,L_moblie,L_password,L_dlnumber;
    String CarUri;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();



        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent1);
            }
        });
        setUpImagePicker();

        signUpEmailLogin();



    }
    private void initUi()
    {

        L_email=findViewById(R.id.LoginEmail);
        L_name=findViewById(R.id.LoginName);
        L_moblie=findViewById(R.id.edt_mobileno);
        L_password=findViewById(R.id.LoginPassword);
        L_dlnumber=findViewById(R.id.LoginDLnumber);
        Register_btn=findViewById(R.id.register_button);
        Login_btn=findViewById(R.id.gotologin_button);

    }

    private void setUpImagePicker()
    {
        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                activityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
            }
        });
    }
    ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        image = data.getData();
                        imageView.setImageURI(image);
                    }
                }
            });

    private void signUpEmailLogin()
    {
        Register_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (L_email.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Email Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (L_password.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Password Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (L_moblie.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Moblie number Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (L_name.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Full Name Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (L_dlnumber.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "DL number Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (image == null)
                {
                    Toast.makeText(MainActivity.this, "Image Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                onUpload();
            }
        });
    }

    private void onUpload(){
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
        StorageReference ref = firebaseStorage.getReference().child("Dl_Pictures/" +L_moblie.getText().toString());
        ref.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        CarUri= uri.toString();
                        signup();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double prog = (snapshot.getBytesTransferred() * 100.0) /snapshot.getTotalByteCount();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signup(){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+L_moblie.getText().toString(),
                60,
                TimeUnit.SECONDS,
                MainActivity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(MainActivity.this, "Phone method", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        Toast.makeText(MainActivity.this, "Verification Phone Send", Toast.LENGTH_SHORT).show();
////                        sendEmailVerification();
                        Intent intent3 = new Intent(getApplicationContext(),verify_otp.class);
                        intent3.setFlags(intent3.FLAG_ACTIVITY_CLEAR_TOP);

                        //passing the date to next activity.

                        intent3.putExtra("dl_number",L_dlnumber.getText().toString());
                        intent3.putExtra("email",L_email.getText().toString());
                        intent3.putExtra("mobile",L_moblie.getText().toString());
                        intent3.putExtra("fname",L_name.getText().toString());
                        intent3.putExtra("image_uri",CarUri);
                        intent3.putExtra("identify_data","Register");
                        intent3.putExtra("otp_mgs",s);
                        startActivity(intent3);

                    }
                }
        );
    }
}