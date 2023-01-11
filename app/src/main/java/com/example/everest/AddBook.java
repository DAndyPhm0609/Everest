package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBook extends AppCompatActivity {

    Button addButton;
    EditText nameText, authorText, priceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        addButton = (Button) findViewById(R.id.button);
        nameText = (EditText) findViewById(R.id.bookName);
        authorText = (EditText) findViewById(R.id.authorName);
        priceText = (EditText) findViewById(R.id.bookPrice);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}