package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;


public class HomePage extends AppCompatActivity {
    private ArrayList<BookData> list;
    private RecyclerView recyclerBook;
    private BookDisplayAdapter bookAdapter ;
    private ImageButton showCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        recyclerBook = findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        createBookList();
        bookAdapter = new BookDisplayAdapter(this,list);
        recyclerBook.setAdapter(bookAdapter);
        recyclerBook.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        showCart= (ImageButton) findViewById(R.id.shoppingCart);
        showCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, ShowCart.class);
                startActivity(i);
            }
        });
    }

    private void createBookList() {
        list.add(new BookData("Thor", R.drawable.hp_cover));
        list.add(new BookData("IronMan", R.drawable.hp_cover));
        list.add(new BookData("IronMan", R.drawable.hp_cover));
        list.add(new BookData("IronMan", R.drawable.hp_cover));
        list.add(new BookData("IronMan", R.drawable.hp_cover));
    }

}