package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SuccessDeli extends AppCompatActivity {
    Button main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_deli);
        main = findViewById(R.id.backMain);
        main.setOnClickListener(view -> {
            Intent intent = new Intent(SuccessDeli.this, HomePage.class);
            startActivity(intent);
            finish();
        });
    }
}