package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity {
    private ArrayList<BookData> list;
    private RecyclerView recyclerBook;
    private BookDisplayAdapter bookAdapter;
    private static final String TAG = "HomePage";

    Button addButton;
    FirebaseFirestore fireStore = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        recyclerBook = findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        createBookList();

        bookAdapter = new BookDisplayAdapter(this, list);
        recyclerBook.setAdapter(bookAdapter);
        recyclerBook.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, AddBook.class);
            }
        });

    }

    private void createBookList() {
        fireStore.collection("books")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot documentSnapshot : snapshotList) {
                            Book book = documentSnapshot.toObject(Book.class);

                            String name = book.getName();
                            list.add(new BookData(name, R.drawable.hp_cover));
                        }
                    }
                });

//        list.add(new BookData("Thor", R.drawable.hp_cover));
//        list.add(new BookData("IronMan", R.drawable.hp_cover));
//        list.add(new BookData("IronMan", R.drawable.hp_cover));
//        list.add(new BookData("IronMan", R.drawable.hp_cover));
//        list.add(new BookData("IronMan", R.drawable.hp_cover));
    }
}