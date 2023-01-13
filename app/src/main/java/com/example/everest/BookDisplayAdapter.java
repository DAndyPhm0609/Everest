package com.example.everest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.everest.R;

import java.util.ArrayList;

public class BookDisplayAdapter extends RecyclerView.Adapter<BookDisplayAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BookData> list;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView bookCover;
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookCover= itemView.findViewById(R.id.bookCover);
            name = itemView.findViewById(R.id.bookName);
        }
    }

    public BookDisplayAdapter(Context context, ArrayList<BookData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View heroView = inflater.inflate(R.layout.item_column, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookData book = list.get(position);
        Glide.with(context).load(book.getBookCover()).into(holder.bookCover);
        holder.name.setText(book.getBookName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
