package com.example.everest;

import android.annotation.SuppressLint;
import android.content.Context;
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
    Context context;
    ArrayList<Book> list;

    public ListViewAdapter(Context context, ArrayList<Book> list){
        this.context = context;
        this.list = list;
    }


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

    @SuppressLint("SetTextI18n")
    public void
    onBindViewHolder(final ListViewAdapter.listViewHolder viewHolder,
                     final int position)
    {
        if (list.get(position) != null) {
            final int index = viewHolder.getAdapterPosition();
            viewHolder.bookName
                    .setText(list.get(position).name);
            viewHolder.bookAuthor
                    .setText(list.get(position).author);
            viewHolder.bookPrice
                    .setText("$" + list.get(position).price);
            viewHolder.bookRating
                    .setText(String.valueOf(list.get(position).rating));
            Picasso.get().load(list.get(position).getUrl()).into(viewHolder.bookCover);
            viewHolder.itemList.setOnClickListener(view -> Toast.makeText(view.getContext(), String.valueOf(index), Toast.LENGTH_SHORT).show());
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
    public void onAttachedToRecyclerView(
            @NonNull RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

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
