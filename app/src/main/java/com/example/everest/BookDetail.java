package com.example.everest;

import static com.example.everest.HomePage.BookArrayList;
import static com.example.everest.HomePage.cartList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookDetail extends AppCompatActivity {
    TextView name, detail, rate;
    ImageButton addCart;
    ImageView cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int position = (int) intent.getExtras().get("index");
        name = findViewById(R.id.detailName);
        name.setText(BookArrayList.get(position).getName());

        detail = findViewById(R.id.description);
        detail.setText(BookArrayList.get(position).getDes());

        rate = findViewById(R.id.detailRate);
        rate.setText(String.valueOf(BookArrayList.get(position).getRating()));

        cover = findViewById(R.id.detailCover);
        Picasso.get().load(BookArrayList.get(position).getUrl()).into(cover);

        addCart = findViewById(R.id.addCart);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartList.add(BookArrayList.get(position));
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}