package com.example.everest;

import static com.example.everest.HomePage.cartList;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class BookDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ImageButton share = (ImageButton) findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton addCart = (ImageButton) findViewById(R.id.addCart);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartList.add(new Book("Harry Potter", "J. K. Rowling", "30", 5.0, "fantasy", "US"));
            }
        });
    }
}