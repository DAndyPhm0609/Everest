package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.ImageButton;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity {
    private ArrayList<BookData> list = new ArrayList<>();
    private RecyclerView recyclerBook;
    private static final String TAG = "HomePage";

    Button addButton;
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private BookDisplayAdapter bookAdapter;
    private ImageButton showCart;
    private ImageButton info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        recyclerBook = findViewById(R.id.recyclerView);

//        addButton = (Button) findViewById(R.id.addButton);
//
//        list = new ArrayList<>();
//        createBookList();
//
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(HomePage.this, AddBook.class);
//                startActivity(i);
//            }
//        });

        bookAdapter = new BookDisplayAdapter(this, list);
        recyclerBook.setAdapter(bookAdapter);
        recyclerBook.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

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
    }


    public void createBookList() {
        //function to
        fireStore.collection("books")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot documentSnapshot : snapshotList) {
                            Book book = documentSnapshot.toObject(Book.class);

                            String name = book.getName();
                            String imgURL = book.getUrl();

                            list.add(new BookData(name, imgURL));
                        }
                    }
                });
    }

    private void addBookToCollection() {
    }
}