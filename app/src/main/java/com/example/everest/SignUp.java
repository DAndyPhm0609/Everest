package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignUp extends AppCompatActivity {
    ImageButton backButton;
    Button submit;
    EditText email, password, name, address, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backButton = (ImageButton) findViewById(R.id.regBack);
        email = (EditText) findViewById(R.id.regEmailText);
        password = (EditText) findViewById(R.id.regPassText);
        name = (EditText) findViewById(R.id.regNameText);
        address = (EditText) findViewById(R.id.regAddressText);
        number = (EditText) findViewById(R.id.regPhoneText);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}