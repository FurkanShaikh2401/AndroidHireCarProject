package com.example.signupapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button selectimgbutton;
    ImageView image;
    int code = 1;

    Button register_btn, gotologin_button;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        gotologin_button = findViewById(R.id.gotologin_button);

        final EditText inputMobile = findViewById(R.id.edt_mobileno);
        register_btn = findViewById(R.id.register_button);
        selectimgbutton = findViewById(R.id.img_select_button);
        image = findViewById(R.id.dlimage);

        selectimgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // logic to call implicit intent
                // creating instance of Intent and use it to open the gallery and fetch the image from it

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Photo"), code);
            }
        });


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(inputMobile.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent2 = new Intent(getApplicationContext(), verify_otp.class);
                intent2.putExtra("mobile", inputMobile.getText().toString());
                startActivity(intent2);

            }
        });

        gotologin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent3);
            }
        });
    }

        //override onActivityResult method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( resultCode == RESULT_OK){
            if( requestCode == code){
                Uri selectedUri = data.getData();
                image.setImageURI(selectedUri);
            }
        }
    }

}