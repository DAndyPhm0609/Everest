package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.widget.ImageButton;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity {

    public static ArrayList<Book> recyclerList = new ArrayList<>();
    public static List<Book> cartList = new ArrayList<>();
    public ArrayList<Book> BookArrayList = new ArrayList<>();
    RecyclerView recyclerBook, listBook;
    private static final String TAG = "HomePage";
    public TextView welcomeText;

    public BookDisplayAdapter bookAdapter;
    public ImageButton showCart;
    public ImageButton info;
    private TextView welcomeView;
    private SearchView searchView;

    Button addButton;

    ListViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Text view to welcome the user
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        String userName;

        //get intent from login to get user's name
        Intent i = getIntent();

        //get username from intent to display in welcome text
        userName = (String) i.getStringExtra("name");
        System.out.println("From homepage" + userName);
        welcomeText.setText(String.format("Hello %s", userName));

        //button image to show the user's shopping cart
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        showCart = (ImageButton) findViewById(R.id.shoppingCart);
        showCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, ShowCart.class);
                startActivity(i);
            }
        });

        //Image button to access our user's profile
        info = (ImageButton) findViewById(R.id.cusInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, UserInfo.class);
                i.putExtra("name", getIntent().getStringExtra("name"));
                i.putExtra("address", getIntent().getStringExtra("address"));
                i.putExtra("phone", getIntent().getStringExtra("phone"));
                startActivity(i);
            }
        });
//                addButton = (Button) findViewById(R.id.addButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(HomePage.this, AddBook.class);
//                startActivity(i);
//            }
//        });
        //call function to generate our adapter view
        generateRecyclerView();
        generateListView();
    }

    //function to use adapter to create our recycler view
    private void generateRecyclerView() {
        recyclerBook = findViewById(R.id.recyclerView);
        recyclerBook.setHasFixedSize(true);
        recyclerBook.setLayoutManager(new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false));
        bookAdapter = new BookDisplayAdapter(getApplication(), recyclerList);
        recyclerBook.setAdapter(bookAdapter);
    }
    //function to create the list view below the recycler view
    private void generateListView() {
        listBook = findViewById(R.id.listView);
        listBook.setHasFixedSize(true);
        listBook.setLayoutManager(new LinearLayoutManager(HomePage.this));
        listAdapter = new ListViewAdapter(getApplication(), BookArrayList);
        listBook.setAdapter(listAdapter);
    }
}