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
        name = findViewById(R.id.delName);
        address = findViewById(R.id.delAddress);
        phone = findViewById(R.id.delPhone);
        confirm = findViewById(delConfirm);
        total = findViewById(R.id.delTotal);
        Intent intent = getIntent();
        cost = (String) intent.getExtras().get("total");
        total.setText(cost);
        confirm.setOnClickListener(view -> {
            Intent intent1 = new Intent(Delivery.this, SuccessDeli.class);
            startActivity(intent1);
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(Delivery.this, ShowCart.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("past total", cost);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}