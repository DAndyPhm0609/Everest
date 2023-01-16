package com.example.everest;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<cartViewHolder> {

    public static boolean isSelectedAll = false;
    List<Book> list
            = Collections.emptyList();
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
        cartViewHolder viewHolder
                = new cartViewHolder(photoView);
        return viewHolder;
    }

    @Override
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
                .setText("$" + String.valueOf(list.get(position).price));
        if (!isSelectedAll) viewHolder.buyCheckBox.setChecked(false);
        else viewHolder.buyCheckBox.setChecked(true);
        viewHolder.bind(position);
    }
    @Override
    public int getItemCount()
    {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static void selectAll(){
        isSelectedAll=true;
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public interface ClickListener{
        void click(int index);
    }
}

