package com.example.everest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    EditText nameText, authorText, priceText, descText, imageText;
    RatingBar bookRating;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        addButton = (Button) findViewById(R.id.button);

        nameText = (EditText) findViewById(R.id.bookName);
        authorText = (EditText) findViewById(R.id.authorName);
        priceText = (EditText) findViewById(R.id.bookPrice);
        imageText = (EditText) findViewById(R.id.imageURL);
        descText = (EditText) findViewById(R.id.bookDes);

        bookRating = (RatingBar) findViewById(R.id.bookRating);

        fireStore = FirebaseFirestore.getInstance();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> book = new HashMap<>();
                book.put("name", nameText.getText().toString());
                book.put("author", authorText.getText().toString());
                book.put("price", priceText.getText().toString());
                book.put("url", imageText.getText().toString());
                book.put("des", descText.getText().toString());
                book.put("rating", bookRating.getRating());


                fireStore.collection("books").add(book).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"book add success", Toast.LENGTH_SHORT).show();
                    }
                });

                finish();
            }
        });
    }
}