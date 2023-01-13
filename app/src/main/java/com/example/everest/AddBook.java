package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddBook extends AppCompatActivity {

    FirebaseFirestore fireStore;

    Button addButton;
    EditText nameText, authorText, priceText;
    RatingBar bookRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        addButton = (Button) findViewById(R.id.button);
        nameText = (EditText) findViewById(R.id.bookName);
        authorText = (EditText) findViewById(R.id.authorName);
        priceText = (EditText) findViewById(R.id.bookPrice);
        bookRating = (RatingBar) findViewById(bookRating);

        fireStore = FirebaseFirestore.getInstance();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> book = new HashMap<>();
                book.put("name", nameText.getText().toString());
                book.put("author", authorText.getText().toString());
                book.put("price", priceText.getText().toString());
                book.put("rating", )

                fireStore.collection("books").add(book).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"success", Toast.LENGTH_SHORT).show();
                    }
                });

                finish();
            }
        });
    }
}