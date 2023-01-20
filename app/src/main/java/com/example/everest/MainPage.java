package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    Button signin, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        signin = (Button) findViewById(R.id.SignIn);
        signup = (Button) findViewById(R.id.SignUp);

        //sign in button
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainPage.this, Login.class);
                startActivity(i);
            }
        });

        //sign up button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainPage.this, SignUp.class);
                startActivity(i);
            }
        });
    }
}