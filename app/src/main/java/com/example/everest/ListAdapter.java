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

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Book> list;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView bookCover, starIcon;
        private TextView name, author, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookCover = itemView.findViewById(R.id.harry);
            name = itemView.findViewById(R.id.bookName1);
            starIcon = itemView.findViewById(R.id.star);
            author = itemView.findViewById(R.id.author);
            rating = itemView.findViewById(R.id.rating);
        }
    }

    public ListAdapter(Context context, ArrayList<Book> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View heroView = inflater.inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = list.get(position);
        Glide.with(context).load(book.getBookCover()).into(holder.bookCover);
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor());
        Glide.with(context).load(book.getRating()).into(holder.starIcon);
        holder.rating.setText(book.getRating());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
