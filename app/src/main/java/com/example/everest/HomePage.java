package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.homepage.R;

import java.util.ArrayList;


public class HomePage extends AppCompatActivity {
    private ArrayList<BookData> list ;
    private RecyclerView recyclerBook;
    private BookDisplayAdapter bookAdapter ;

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

    }

    private void createBookList() {
        list.add(new BookData("Thor", R.drawable.hp_cover));
        list.add(new BookData("IronMan", R.drawable.hp_cover));
        list.add(new BookData("IronMan", R.drawable.hp_cover));
        list.add(new BookData("IronMan", R.drawable.hp_cover));
        list.add(new BookData("IronMan", R.drawable.hp_cover));
    }
}