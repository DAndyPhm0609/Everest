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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Login extends AppCompatActivity {
    Button login;
    ImageButton back;
    EditText email, password;
    TextView emailV, passV;
    FirebaseAuth mAuth;
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    String userName;

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
        String emailLogin = email.getText().toString();
        String passwordLogin = password.getText().toString();

        if (TextUtils.isEmpty(emailLogin)) {
            email.setError("Email cannot be empty");
            email.requestFocus();
        } else if (TextUtils.isEmpty(passwordLogin)) {
            password.setError("Password cannot be empty");
            password.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(emailLogin, passwordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        fireStore.collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                                for (DocumentSnapshot documentSnapshot : snapshotList) {
                                    User user = documentSnapshot.toObject(User.class);

                                    String email = user.getEmail();
                                    if(emailLogin.equals(email)){
                                        userName = user.getName();
                                        System.out.println(userName);

                                        Intent i = new Intent(Login.this, HomePage.class);
                                        i.putExtra("name", userName);
                                        startActivity(i);
                                        break;
                                    }
                                }
                            }
                        });
                    } else {
                        Toast.makeText(Login.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}