package com.example.everest;

import static com.example.everest.HomePage.cartList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ShowCart extends AppCompatActivity {
    CartListAdapter adapter;
    RecyclerView recyclerView;
    static CheckBox selectAll;
    static TextView amount;
    Button checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //go back button
        selectAll = findViewById(R.id.selectAll);
        amount = findViewById(R.id.amount);
        checkout = findViewById(R.id.checkout);
        populateRecyclerView();
        onClickEvent();
    }
    //auto update total price when user press check box
    public static void setAmount(int total) {
        amount.setText("$" + total);
    }

    //set text Select All check box
    public static void setSelectAll() {
        selectAll.setChecked(true);
        selectAll.setText(R.string.deselect_all);
    }

    //set text Deselect All check box
    public static void setDeselectAll() {
        selectAll.setChecked(false);
        selectAll.setText(R.string.select_all);
    }

    //initial second recyclerView
    private void populateRecyclerView() {
        recyclerView = findViewById(R.id.cartList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowCart.this));
        adapter = new CartListAdapter(cartList, getApplication());
        recyclerView.setAdapter(adapter);
        adapter.calcTotal();
    }

    private void onClickEvent() {
        selectAll.setOnClickListener(view -> {
            //Check the current text of Select Button
            if (selectAll.getText().toString().equals(getResources().getString(R.string.select_all))) {
                //If Text is Select All then loop to all array List items and check all of them
                for (int i = 0; i < cartList.size(); i++)
                    adapter.checkCheckBox(i, true);
                //After checking all items change button text
                selectAll.setText(getResources().getString(R.string.deselect_all));
            } else {
                //If button text is Deselect All remove check from all items
                adapter.removeSelection();
                //After checking all items change button text
                selectAll.setText(getResources().getString(R.string.select_all));
                setAmount(0);
            }
        });

        //check out button to delivery layout
        checkout.setOnClickListener(view -> {
            if (amount.getText().toString().equals("$0")){
                Toast.makeText(ShowCart.this, "You must select at least 1 item.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(ShowCart.this, Delivery.class);
                intent.putExtra("total", amount.getText().toString());
                startActivityForResult(intent, 100);
            }
        });
    }


    //go back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //get back straight to homepage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 100) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }


}