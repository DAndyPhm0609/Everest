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
import android.widget.TextView;
import android.widget.Toast;

import com.example.everest.databinding.ActivityHomePageBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity {

    public static ArrayList<Book> recyclerList = new ArrayList<>();
    public static List<Book> cartList = new ArrayList<>();
    public ArrayList<Book> BookArrayList = new ArrayList<>();
    private RecyclerView recyclerBook;
    private static final String TAG = "HomePage";
    private TextView welcomeView;

    Button addButton;
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private BookDisplayAdapter bookAdapter;
    private ImageButton showCart;
    private ImageButton info;


    ListViewAdapter listAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        welcomeView = (TextView) findViewById(R.id.welcomeText);
        String userName;

        Intent i = getIntent();
        userName = (String) i.getStringExtra("name");
        System.out.println("From homepage" + userName);
        welcomeView.setText(String.format("Hello %s", userName));

        showCart = (ImageButton) findViewById(R.id.shoppingCart);
        showCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, ShowCart.class);
                startActivity(i);
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
//                addButton = (Button) findViewById(R.id.addButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(HomePage.this, AddBook.class);
//                startActivity(i);
//            }
//        });
        generateRecyclerView();
        generateListView();
    }

    private void generateRecyclerView() {
        recyclerBook = findViewById(R.id.recyclerView);
        recyclerBook.setHasFixedSize(true);
        recyclerBook.setLayoutManager(new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false));
        bookAdapter = new BookDisplayAdapter(getApplication(), recyclerList);
        recyclerBook.setAdapter(bookAdapter);
    }

    private void generateListView() {
        listAdapter = new ListViewAdapter(HomePage.this, BookArrayList);
        listView = findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
    }

}