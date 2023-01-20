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

    public static ArrayList<BookData> recyclerList = new ArrayList<>();
    public static List<Book> cartList = new ArrayList<>();
    public ArrayList<BookCardDetail> bookCardDetailArrayList = new ArrayList<>();
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
        createNewList();
        listAdapter = new ListViewAdapter(bookCardDetailArrayList);
        listView = findViewById(R.id.listView);
        listView.setAdapter(listAdapter);

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
    }

    private void generateRecyclerView() {
        createBookList();
        recyclerBook = findViewById(R.id.recyclerView);
        recyclerBook.setHasFixedSize(true);
        recyclerBook.setLayoutManager(new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false));
        bookAdapter = new BookDisplayAdapter(getApplication(), recyclerList);
        recyclerBook.setAdapter(bookAdapter);
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
                            String imgURL = book.getUrl();
                            recyclerList.add(new BookData(name, imgURL));
                        }
                    }
                });
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
    }
}