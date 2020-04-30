package com.example.foodrocket;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Item extends AppCompatActivity {

    private TextView itemTitle, itemPrice, itemDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);

        itemTitle = (TextView) findViewById(R.id.title);
        itemDescription = (TextView) findViewById(R.id.desc);
        itemPrice = (TextView) findViewById(R.id.price);

        itemTitle.setText(getIntent().getStringExtra("ItemName"));
        itemDescription.setText(getIntent().getStringExtra("ItemDesc"));
        itemPrice.setText(getIntent().getStringExtra("ItemPrice"));
    }
}
