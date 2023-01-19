package com.example.everest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Book> {

    public ListViewAdapter(Context context, ArrayList<Book> list) {
        super(context, R.layout.item_list, list);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Book book = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        ImageView star = convertView.findViewById(R.id.star);
        TextView name = convertView.findViewById(R.id.bookName1);
        TextView author = convertView.findViewById(R.id.author);
        TextView rating = convertView.findViewById(R.id.rating);
        ImageView bookCover = convertView.findViewById(R.id.harry);
        TextView price = convertView.findViewById(R.id.price);
        ImageButton wishList = convertView.findViewById(R.id.wishList);



        //star.setImageURI(book.url);
        //bookCover.setImageURI(book.url);
        //wishList.setImageButton(book.url);
        name.setText(book.name);
        author.setText(book.author);
        rating.setText(book.rating.toString());
        price.setText(book.price);

        return super.getView(position, convertView, parent);
    }
}
