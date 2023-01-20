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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class BookDetail extends AppCompatActivity {
    TextView name, detail, rate, author, price;
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

        author = findViewById(R.id.detailAuthor);
        author.setText(BookArrayList.get(position).getAuthor());

        price = findViewById(R.id.detailPrice);
        price.setText(BookArrayList.get(position).getPrice());

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
                Toast.makeText(BookDetail.this,"Added to cart", Toast.LENGTH_SHORT).show();
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