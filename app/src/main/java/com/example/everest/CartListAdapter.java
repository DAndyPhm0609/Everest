package com.example.everest;

import static com.example.everest.HomePage.cartList;
import static com.example.everest.ShowCart.setAmount;
import static com.example.everest.ShowCart.setDeselectAll;
import static com.example.everest.ShowCart.setSelectAll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import com.squareup.picasso.Picasso;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.cartViewHolder> {
    static List<Book> list;
    static SparseBooleanArray itemStateArray= new SparseBooleanArray();
    Context context;

    public CartListAdapter(List<Book> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //create a holder to store all element exist in cart list
    @NonNull
    @Override
    public cartViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
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

    //set all element exist in cart list
    @SuppressLint("SetTextI18n")
    public void
    onBindViewHolder(final cartViewHolder viewHolder,
                     final int position)
    {
        final int index = viewHolder.getAdapterPosition();
        Picasso.get().load(list.get(position).getUrl()).into(viewHolder.cartPhoto);
        viewHolder.cartName
                .setText(list.get(position).name);
        viewHolder.cartAuthor
                .setText(list.get(position).author);
        viewHolder.cartPrice
                .setText("$" + list.get(position).price);
        viewHolder.buyCheckBox.setChecked(itemStateArray.get(position));
        viewHolder.buyCheckBox.setOnClickListener(v -> checkCheckBox(index, !itemStateArray.get(index)));
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

    @SuppressLint("NotifyDataSetChanged")
    public void removeSelection() {
        itemStateArray = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    //display tick when the user press check box
    public void checkCheckBox(int position, boolean value) {
        if (value)
            itemStateArray.put(position, true);
        else
            itemStateArray.delete(position);
        calcTotal();
    }

    //calculate the total price when user press check out button
    @SuppressLint("NotifyDataSetChanged")
    public void calcTotal() {
        int num = 0;
        int cost = 0;
        int total = 0;
        for (int i = 0; i < cartList.size(); i++) {
            if (!itemStateArray.get(i)) {
                num++;
            } else {
                try {
                    cost = Integer.parseInt(list.get(i).price);
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                total += cost;
            }
        }
        //check if user choose any item to check to display proper message
        if (cartList.isEmpty()) {
            setDeselectAll();
        } else if (num == 0) {
            setSelectAll();
            setAmount(0);
        } else {
            setDeselectAll();
        }
        setAmount(total);
        notifyDataSetChanged();
    }

    //initial all element exist in the cart list
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


