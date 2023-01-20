package com.example.everest;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class UserInfo extends AppCompatActivity {
    ImageButton backButton;
    TextView name,address,phone;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String Uid;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);


        name = (TextView)findViewById(R.id.nameView);
        address = (TextView) findViewById(R.id.addressView);
        phone = (TextView) findViewById(R.id.phoneView);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        Uid = mAuth.getCurrentUser().getUid();

        logout = (Button) findViewById(R.id.logOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserInfo.this, MainPage.class);
                startActivity(i);
                finish();
            }
        });

        backButton = (ImageButton) findViewById(R.id.backBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fStore.collection("users").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for(QueryDocumentSnapshot document:task.getResult()){
                            Log.d(TAG, document.getId() + "=>" + document.getData());
                            name.setText(getIntent().getStringExtra("name"));
                            address.setText(getIntent().getStringExtra("address"));
                            phone.setText(getIntent().getStringExtra("phone"));
                        }
                    }else{
                        Log.w(TAG,"Error getting User", task.getException());
                    }

                });

    }
}