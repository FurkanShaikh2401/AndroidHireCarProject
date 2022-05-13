package com.example.HireCar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;


public class welcome_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_screen);
    }

    // display login screen when welcome_login_button is clicked
    public void callSignUpScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.welcome_signup_btn), "transition_signup");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(welcome_screen.this, pairs);
        startActivity(intent, options.toBundle());
    }

    // display login screen when welcome_login_button is clicked
    public void callLoginScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.welcome_login_btn), "transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(welcome_screen.this, pairs);
        startActivity(intent, options.toBundle());

    }
}