package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;


public class HomePage extends AppCompatActivity {
    private ArrayList<BookData> list;
    private RecyclerView recyclerBook;
    private BookDisplayAdapter bookAdapter ;
    private ImageButton cart;
    private ImageButton info;

    private ArrayList<Book> displayList;
    private RecyclerView recyclerList;
    private ListAdapter listAdapter;

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

        recyclerList = findViewById(R.id.recycler_view2);
        displayList = new ArrayList<>();
        createList();
        recyclerList.setAdapter(listAdapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));

        cart = (ImageButton) findViewById(R.id.shoppingcart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(HomePage.this,);
                Toast.makeText(HomePage.this, "shopping cart", Toast.LENGTH_SHORT).show();
            }
        });

        info = (ImageButton) findViewById(R.id.cusInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(HomePage.this,);
                Toast.makeText(HomePage.this, "user info", Toast.LENGTH_SHORT).show();
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

    private void createList() {
        displayList.add(new Book("Harry Porter", "J.K.Rowling", 30,"5.0", R.drawable.hp_cover, R.drawable.star_icon));
        displayList.add(new Book("Harry Porter", "J.K.Rowling", 30,"5.0", R.drawable.hp_cover, R.drawable.star_icon));
        displayList.add(new Book("Harry Porter", "J.K.Rowling", 30,"5.0", R.drawable.hp_cover, R.drawable.star_icon));
        displayList.add(new Book("Harry Porter", "J.K.Rowling", 30,"5.0", R.drawable.hp_cover, R.drawable.star_icon));
    }
}