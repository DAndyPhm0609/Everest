package com.example.everest;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class cartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    static CheckBox buyCheckBox;
    ImageView cartPhoto;
    TextView cartName;
    TextView cartAuthor;
    TextView cartPrice;
    View view;

    cartViewHolder(View itemView)
    {
        super(itemView);
        buyCheckBox
                = (CheckBox) itemView
                .findViewById(R.id.buyCheckBox);
        cartPhoto
                = (ImageView) itemView
                .findViewById(R.id.cartPhoto);
        cartName
                = (TextView)itemView
                .findViewById(R.id.cartName);
        cartAuthor
                = (TextView)itemView
                .findViewById(R.id.cartAuthor);
        cartPrice
                = (TextView)itemView
                .findViewById(R.id.cartPrice);
        view  = itemView;
    }
    void bind(int position) {
        // use the sparse boolean array to check
        if (!CartListAdapter.itemStateArray.get(position, false)) {
            buyCheckBox.setChecked(false);}
        else {
            buyCheckBox.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        int adapterPosition = getAdapterPosition();
        if (!CartListAdapter.itemStateArray.get(adapterPosition, false)) {
            buyCheckBox.setChecked(true);
            CartListAdapter.itemStateArray.put(adapterPosition, true);
        }
        else  {
            buyCheckBox.setChecked(false);
            CartListAdapter.itemStateArray.put(adapterPosition, false);
        }
    }
    public static void onSelectAllClick(boolean clicked){
        if (clicked) {
            for (int i = 0; i < CartListAdapter.itemStateArray.size(); i++) {
                buyCheckBox.setChecked(false);
                CartListAdapter.itemStateArray.put(i, false);
            }
        } else {
            for (int i = 0; i < CartListAdapter.itemStateArray.size(); i++) {
                buyCheckBox.setChecked(true);
                CartListAdapter.itemStateArray.put(i, true);
            }
        }
    }
}
