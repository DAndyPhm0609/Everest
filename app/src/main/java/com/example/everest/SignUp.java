package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {
    Button submit;
    TextView name,pass;
    EditText regName,regPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        submit = (Button) findViewById(R.id.submit);

        regName = (EditText) findViewById(R.id.regEmailText);
        regPass = (EditText) findViewById(R.id.regPassText);
        name = (TextView) findViewById(R.id.regEmailId);
        pass = (TextView) findViewById(R.id.regPassId);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
    }