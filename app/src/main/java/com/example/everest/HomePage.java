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
    RecyclerView recyclerBook, listBook;
    private static final String TAG = "HomePage";
    private TextView welcomeView;

    Button addButton;
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private BookDisplayAdapter bookAdapter;
    private ImageButton showCart;
    private ImageButton info;

    ListViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        createBookList();
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
        listBook = findViewById(R.id.listView);
        listBook.setHasFixedSize(true);
        listBook.setLayoutManager(new LinearLayoutManager(HomePage.this));
        listAdapter = new ListViewAdapter(getApplication(), BookArrayList);
        listBook.setAdapter(listAdapter);
    }
    public void createBookList() {
        fireStore.collection("books")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot documentSnapshot : snapshotList) {
                            Book book = documentSnapshot.toObject(Book.class);

                            String name = book.getName();
                            String author = book.getAuthor();
                            String price = book.getPrice();
                            String des = book.getDes();
                            Double rating = book.getRating();
                            String imgURL = book.getUrl();
                            System.out.println(name);
                            System.out.println(author);
                            System.out.println(price);
                            System.out.println(rating);
                            System.out.println(imgURL);
                            recyclerList.add(new Book(name, author, price, rating, des, imgURL));
                            BookArrayList.add(new Book(name, author, price, rating, des, imgURL));
                        }
                    }
                });
    }
}