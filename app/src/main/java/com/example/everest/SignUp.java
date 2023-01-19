package com.example.everest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    Button submit;
    EditText email, password, name, address, number;
    FirebaseAuth mAuth;
    FirebaseFirestore fireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email = (EditText) findViewById(R.id.regEmailText);
        password = (EditText) findViewById(R.id.regPassText);
        name = (EditText) findViewById(R.id.regNameText);
        address = (EditText) findViewById(R.id.regAddressText);
        number = (EditText) findViewById(R.id.regPhoneText);
        submit = (Button) findViewById(R.id.submit);

        mAuth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();

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
                        Map<String, Object> user = new HashMap<>();
                        user.put("name", Name);
                        user.put("password", Password);
                        user.put("address", Address);
                        user.put("email", Email);
                        user.put("phone", Phone);

                        fireStore.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(SignUp.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(SignUp.this, MainPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}