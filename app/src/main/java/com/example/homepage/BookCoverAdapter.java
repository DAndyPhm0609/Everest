package com.example.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookCoverAdapter extends RecyclerView.Adapter<BookCoverAdapter.ViewHolder>{
    private Context context;
    private ArrayList<BookData> list;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView bookCover;
        private TextView bookName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookCover = itemView.findViewById(R.id.imageBook);
            bookName = itemView.findViewById(R.id.bookName);
        }
    }

    public BookCoverAdapter(Context context, ArrayList<BookData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View bookView = layoutInflater.inflate(R.layout.item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(bookView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookData book = list.get(position);
        Glide.with(context).load(book.getImageBook()).into(holder.bookCover);
        holder.bookName.setText(book.getBookName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
