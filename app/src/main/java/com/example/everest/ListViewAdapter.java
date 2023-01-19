package com.example.everest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//public class ListViewAdapter extends ArrayAdapter<Book> {
//
//    public ListViewAdapter(Context context, ArrayList<Book> list) {
//        super(context, R.layout.item_list, list);
//
//    }

class ListViewAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    ArrayList<BookCardDetail> bookList;

    public ListViewAdapter(Context context, ArrayList<BookCardDetail> bookList){
        this.context = context;
        this.bookList = bookList;
    }

    ListViewAdapter(ArrayList<BookCardDetail> bookList) {
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewRequest;
        if (convertView == null) {
            viewRequest = View.inflate(parent.getContext(), R.layout.item_list, null);
        } else viewRequest = convertView;

        BookCardDetail book = (BookCardDetail) getItem(position);

        ImageView star = convertView.findViewById(R.id.cardStar);
        TextView name = convertView.findViewById(R.id.cardBookName);
        TextView author = convertView.findViewById(R.id.cardAuthorName);
        TextView rating = convertView.findViewById(R.id.cardBookRating);
        ImageView bookCover = convertView.findViewById(R.id.cardImageUrl);
        TextView price = convertView.findViewById(R.id.cardBookPrice);
        ImageButton wishList = convertView.findViewById(R.id.cardWishList);


        Picasso.get().load("https://uxwing.com/wp-content/themes/uxwing/download/arts-graphic-shapes/star-icon.png").into(star);
        Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-rR5-UUarzgaOnGCMMC8OV06K2zwdd_ZJcX60BP0&s").into(wishList);
        Picasso.get().load(book.url).into(bookCover);
        name.setText(book.name);
        author.setText(book.author);
        rating.setText(book.rating.toString());
        price.setText(book.price);

        return viewRequest;
    }
}
