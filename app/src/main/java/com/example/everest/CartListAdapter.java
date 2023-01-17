package com.example.everest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.cartViewHolder> {
    List<Book> list;
    static SparseBooleanArray itemStateArray= new SparseBooleanArray();
    Context context;
    ClickListener listener;
    public CartListAdapter(List<Book> list,
                                 Context context,ClickListener listener)
    {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public cartViewHolder
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
                .inflate(R.layout.cart_card,
                        parent, false);
        return new cartViewHolder(photoView);
    }

    @SuppressLint("SetTextI18n")
    public void
    onBindViewHolder(final cartViewHolder viewHolder,
                     final int position)
    {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.cartName
                .setText(list.get(position).name);
        viewHolder.cartAuthor
                .setText(list.get(position).author);
        viewHolder.cartPrice
                .setText("$" + list.get(position).price);
        viewHolder.buyCheckBox.setChecked(itemStateArray.get(position));
        viewHolder.buyCheckBox.setOnClickListener(v -> checkCheckBox(index, !itemStateArray.get(index)));
        viewHolder.cartCard.setOnClickListener(view -> Toast.makeText(view.getContext(),String.valueOf(index),Toast.LENGTH_SHORT).show());
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
    public interface ClickListener{
    }

    @SuppressLint("NotifyDataSetChanged")
    public void removeSelection() {
        itemStateArray = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void checkCheckBox(int position, boolean value) {
        if (value)
            itemStateArray.put(position, true);
        else
            itemStateArray.delete(position);
        notifyDataSetChanged();
    }

    static class cartViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout cartCard;
        CheckBox buyCheckBox;
        ImageView cartPhoto;
        TextView cartName;
        TextView cartAuthor;
        TextView cartPrice;
        @SuppressLint("StaticFieldLeak")
        static View view;
        cartViewHolder(View itemView)
        {
            super(itemView);
            buyCheckBox
                    = itemView
                    .findViewById(R.id.buyCheckBox);
            cartPhoto
                    = itemView
                    .findViewById(R.id.cartPhoto);
            cartName
                    = itemView
                    .findViewById(R.id.cartName);
            cartAuthor
                    = itemView
                    .findViewById(R.id.cartAuthor);
            cartPrice
                    = itemView
                    .findViewById(R.id.cartPrice);
            view  = itemView;
            cartCard
                    = itemView
                    .findViewById(R.id.cartCard);
        }
    }
}


