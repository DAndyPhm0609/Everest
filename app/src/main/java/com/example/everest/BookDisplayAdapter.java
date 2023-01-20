package com.example.everest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class BookDisplayAdapter extends RecyclerView.Adapter<BookDisplayAdapter.ViewHolder> {
    Context context;
    ArrayList<Book> list;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookCover;
        TextView name;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookCover= itemView.findViewById(R.id.bookCover);
            name = itemView.findViewById(R.id.bookName);
            view = itemView;
        }
    }
    public BookDisplayAdapter(Context context, ArrayList<Book> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);
        // Inflate the layout
        View photoView = inflater.inflate(R.layout.item_column, parent, false);
        return new BookDisplayAdapter.ViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Book book = list.get(position);
        Picasso.get().load(book.getUrl()).into(holder.bookCover);
        holder.name.setText(book.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public void onAttachedToRecyclerView(
            @NonNull RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
