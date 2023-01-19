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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity {

    public static ArrayList<BookData> recyclerList;
    public static ArrayList<BookCardDetail> bookCardDetailArrayList = new ArrayList<>();
    public static List<Book> cartList = new ArrayList<>();
    private RecyclerView recyclerBook;
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
        Intent i = getIntent();
        String userName = (String) i.getStringExtra("name");
        welcomeView.setText(String.format("Hello %s", userName));

        System.out.println("Before create book list");
        createRecyclerView();
        System.out.println("After create book list");
        // createListView();

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
        //        addButton = (Button) findViewById(R.id.addButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(HomePage.this, AddBook.class);
//                startActivity(i);
//            }
//        });
    }

    public void createListView(){
        createNewList();
        listAdapter = new ListViewAdapter(getApplicationContext(),bookCardDetailArrayList);
        listView = findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
    }

    public void createRecyclerView(){
        createBookList();
        recyclerBook = findViewById(R.id.recyclerView);
        bookAdapter = new BookDisplayAdapter(this, recyclerList);
        recyclerBook.setAdapter(bookAdapter);
        recyclerBook.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void createBookList() {
        recyclerList = new ArrayList<>();
        fireStore.collection("books")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot documentSnapshot : snapshotList) {
                            Book book = documentSnapshot.toObject(Book.class);

                            String name = book.getName();
                            String imgURL = book.getUrl();

                            System.out.println(name);
                            System.out.println(imgURL);

                            recyclerList.add(new BookData(name, imgURL));
                            System.out.println("add success");
                        }
                    }
                });
//        recyclerList.add(new BookData("Harry Poppy", R.drawable.hp_cover));
//        recyclerList.add(new BookData("Harry Poppy", R.drawable.hp_cover));
//        recyclerList.add(new BookData("Harry Poppy", R.drawable.hp_cover));
    }

    public void createNewList() {
        fireStore.collection("books")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot documentSnapshot : snapshotList) {
                            BookCardDetail book = documentSnapshot.toObject(BookCardDetail.class);

                            String name = book.getName();
                            String author = book.getAuthor();
                            String price = book.getPrice();
                            Double rating = book.getRating();
                            String imgURL = book.getUrl();
                            String star = "https://uxwing.com/wp-content/themes/uxwing/download/arts-graphic-shapes/star-icon.png";
                            String wishList = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-rR5-UUarzgaOnGCMMC8OV06K2zwdd_ZJcX60BP0&s";

                            System.out.println(name);
                            System.out.println(author);
                            System.out.println(price);
                            System.out.println(rating);
                            System.out.println(imgURL);

                            bookCardDetailArrayList.add(new BookCardDetail(name, author, price, rating, star, imgURL, wishList));
                        }
                    }
                });
//        bookCardDetailArrayList.add(new BookCardDetail("name", "author", "price", 4.0, "https://uxwing.com/wp-content/themes/uxwing/download/arts-graphic-shapes/star-icon.png", imgURL, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-rR5-UUarzgaOnGCMMC8OV06K2zwdd_ZJcX60BP0&s"));

    }
}