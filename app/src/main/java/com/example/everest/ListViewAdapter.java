package com.example.everest;

import static com.example.everest.HomePage.BookArrayList;
import static com.example.everest.HomePage.cartList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.listViewHolder> {
    static Context context;
    ArrayList<Book> list;

    public ListViewAdapter(Context context, ArrayList<Book> list){
        this.context = context;
        this.list = list;
    }

    //create view holder to store all element
    @NonNull
    @Override
    public ListViewAdapter.listViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType)
    {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);
        // Inflate the layout
        View photoView
                = inflater
                .inflate(R.layout.item_list,
                        parent, false);
        return new ListViewAdapter.listViewHolder(photoView);
    }

    //set all element exist in list view
    @SuppressLint("SetTextI18n")
    public void
    onBindViewHolder(final ListViewAdapter.listViewHolder viewHolder,
                     final int position)
    {
        final int index = viewHolder.getAdapterPosition();
        if (list.get(position) != null) {
            viewHolder.bookName
                    .setText(list.get(position).name);
            viewHolder.bookAuthor
                    .setText(list.get(position).author);
            viewHolder.bookPrice
                    .setText("$" + list.get(position).price);
            viewHolder.bookRating
                    .setText(String.valueOf(list.get(position).rating));
            Picasso.get().load(list.get(position).getUrl()).into(viewHolder.bookCover);
            viewHolder.itemList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BookDetail.class);
                    intent.putExtra("index", index);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

            //add to cart button
            viewHolder.addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartList.add(BookArrayList.get(index));
                    Toast.makeText(context,"Added to cart", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    //initial all element for vertical list view
     static class listViewHolder extends RecyclerView.ViewHolder{
        ImageView bookCover;
        TextView bookName;
        TextView bookAuthor;
        TextView bookPrice;
        TextView bookRating;
        ImageButton addCart;
        RelativeLayout itemList;
        View view;
        listViewHolder(View itemView)
        {
            super(itemView);
            bookCover
                    = itemView
                    .findViewById(R.id.cardImageUrl);
            bookName
                    = itemView
                    .findViewById(R.id.cardBookName);
            bookAuthor
                    = itemView
                    .findViewById(R.id.cardAuthorName);
            bookPrice
                    = itemView
                    .findViewById(R.id.cardBookPrice);
            bookRating
                    = itemView
                    .findViewById(R.id.cardBookRating);
            addCart
                    = itemView.findViewById(R.id.addCartButton);
            view  = itemView;
            itemList
                    = itemView
                    .findViewById(R.id.itemList);
        }
    }
}
