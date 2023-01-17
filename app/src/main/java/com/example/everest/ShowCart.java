package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowCart extends AppCompatActivity {

    public static List<Book> cartList = new ArrayList<>();
    CartListAdapter adapter;
    RecyclerView recyclerView;
    static CheckBox selectAll;
    static TextView amount;
    Button checkout;
    CartListAdapter.ClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        selectAll = findViewById(R.id.selectAll);
        amount = findViewById(R.id.amount);
        populateRecyclerView();
        onClickEvent();
    }
    public static void setAmount(int total) {
        amount.setText("$" + total);
    }
    public static void setSelectAll() {
        selectAll.setChecked(true);
        selectAll.setText(R.string.deselect_all);
    }
    public static void setDeselectAll() {
        selectAll.setChecked(false);
        selectAll.setText(R.string.select_all);
    }
    private void populateRecyclerView() {
        recyclerView = findViewById(R.id.cartList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowCart.this));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));
        cartList.add(new Book("Harry Potter", "J. K. Rowling", 30, "5.0", "fantasy", "US", 1));

        adapter = new CartListAdapter(cartList, getApplication(),listener);
        recyclerView.setAdapter(adapter);
        listener = new CartListAdapter.ClickListener() {

        };
    }

    private void onClickEvent() {
        selectAll.setOnClickListener(view -> {
            //Check the current text of Select Button
            if (selectAll.getText().toString().equals(getResources().getString(R.string.select_all))) {
                //If Text is Select All then loop to all array List items and check all of them
                for (int i = 0; i < cartList.size(); i++)
                    adapter.checkCheckBox(i, true);
                //After checking all items change button text
                selectAll.setText(getResources().getString(R.string.deselect_all));
            } else {
                //If button text is Deselect All remove check from all items
                adapter.removeSelection();
                //After checking all items change button text
                selectAll.setText(getResources().getString(R.string.select_all));
                setAmount(0);
            }
        });
    }
}