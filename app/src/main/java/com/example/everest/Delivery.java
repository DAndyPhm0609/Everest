package com.example.everest;

import static com.example.everest.R.id.delConfirm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Delivery extends AppCompatActivity {
    EditText name, address, phone;
    TextView total;
    Button confirm;
    String cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initial all elements in this layout
        name = findViewById(R.id.delName);
        address = findViewById(R.id.delAddress);
        phone = findViewById(R.id.delPhone);
        confirm = findViewById(delConfirm);
        total = findViewById(R.id.delTotal);
        Intent intent = getIntent();
        cost = (String) intent.getExtras().get("total");
        name.setText(HomePage.userName);
        address.setText(HomePage.userAddress);
        phone.setText(HomePage.userPhone);
        total.setText(cost);

        //display successful purchase
        confirm.setOnClickListener(view -> {
            Intent intent1 = new Intent(Delivery.this, SuccessDeli.class);
            startActivityForResult(intent1, 200);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //go back to homepage when press main page button
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 200) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        }
    }
}