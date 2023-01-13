package com.example.everest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    ImageButton backButton;
    Button submit;
    EditText email, password, name, address, number;
    FirebaseAuth mAuth;

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
        submit = (Button) findViewById(R.id.submit);

        mAuth = FirebaseAuth.getInstance();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this, MainPage.class);
                startActivity(i);
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    private void createUser() {
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String Name = name.getText().toString();
        String Address = address.getText().toString();
        String Phone = number.getText().toString();

        if (TextUtils.isEmpty(Email)) {
            email.setError("Email cannot be empty");
            email.requestFocus();
        } else if (TextUtils.isEmpty(Password)) {
            password.setError("Password cannot be empty");
            password.requestFocus();
        } else if (TextUtils.isEmpty(Name)) {
            name.setError("Name cannot be empty");
            name.requestFocus();
        } else if (TextUtils.isEmpty(Address)) {
            address.setError("Address cannot be empty");
            address.requestFocus();
        } else if (TextUtils.isEmpty(Phone)) {
            number.setError("Please enter your phone number");
            number.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "User registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUp.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}