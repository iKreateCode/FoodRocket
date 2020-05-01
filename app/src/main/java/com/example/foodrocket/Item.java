package com.example.foodrocket;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Item extends AppCompatActivity {

    private TextView itemTitle, itemPrice, itemDescription;
    private Button cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);

        itemTitle = (TextView) findViewById(R.id.title);
        itemDescription = (TextView) findViewById(R.id.desc);
        itemPrice = (TextView) findViewById(R.id.price);
        cartButton = (Button) findViewById(R.id.addCartButton);

        itemTitle.setText(getIntent().getStringExtra("ItemName"));
        itemDescription.setText(getIntent().getStringExtra("ItemDesc"));
        itemPrice.setText(getIntent().getStringExtra("ItemPrice"));


        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment.addToCart(getIntent().getSerializableExtra("model"), getApplicationContext());
            }
        });
    }

    public static void addToCart(MenuItem item, Context context) {

    }
}
