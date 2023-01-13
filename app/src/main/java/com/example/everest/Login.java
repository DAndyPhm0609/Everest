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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button login;
    ImageButton back;
    EditText email, password;
    TextView emailV, passV;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.loginId);
        back = (ImageButton) findViewById(R.id.backButton);
        email = (EditText) findViewById(R.id.EmailText);
        password = (EditText) findViewById(R.id.regPassText);
        emailV = (TextView) findViewById(R.id.regEmailId);
        passV = (TextView) findViewById(R.id.regPassId);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, HomePage.class);
                startActivity(i);

            }
        });
    }

    private void loginUser() {
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        if (TextUtils.isEmpty(Email)) {
            email.setError("Email cannot be empty");
            email.requestFocus();
        } else if (TextUtils.isEmpty(Password)) {
            password.setError("Password cannot be empty");
            password.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(Login.this, HomePage.class));
                    } else {
                        Toast.makeText(Login.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}