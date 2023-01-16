package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowCart extends AppCompatActivity {

    public static List<Book> cartList = new ArrayList<>();
    CartListAdapter adapter;
    RecyclerView recyclerView;
    CheckBox selectAll;
    TextView total, amount;
    Button checkout;
    CartListAdapter.ClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        recyclerView = findViewById(R.id.cartList);
        selectAll = findViewById(R.id.selectAll);
        total = findViewById(R.id.total);
        amount = findViewById(R.id.amount);
        checkout = findViewById(R.id.checkout);
        listener = new CartListAdapter.ClickListener() {
            @Override
            public void click(int index){
//                Intent edit = new Intent(MainActivity.this, BookDetail.class);
//                edit.putExtra("index",index);
//                startActivityForResult(edit,300);
                Toast.makeText(ShowCart.this,Integer.toString(index), Toast.LENGTH_SHORT).show();
            }
        };
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));

        adapter = new CartListAdapter(cartList, getApplication(),listener);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartListAdapter.selectAll();
                adapter.notifyDataSetChanged();
            }
        } );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowCart.this));
        adapter.notifyDataSetChanged();
    }
}