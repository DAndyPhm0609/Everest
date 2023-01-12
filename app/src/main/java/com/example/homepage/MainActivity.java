package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<BookData> list;
    private RecyclerView recyclerBook;
    private BookCoverAdapter bookCoverAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerBook = findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        createBookList();
        bookCoverAdapter = new BookCoverAdapter(this, list);
        recyclerBook.setAdapter(bookCoverAdapter);
        recyclerBook.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createBookList() {
        list.add(new BookData("Harry Porter", R.drawable.hp_cover));
        list.add(new BookData("Harry Porter", R.drawable.hp_cover));
    }
}