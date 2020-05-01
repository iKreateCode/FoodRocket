package com.example.foodrocket;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Item extends AppCompatActivity {

    private TextView itemTitle, itemPrice, itemDescription;
    private Button cartButton;
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);

        String json_item = (String) getIntent().getSerializableExtra("selected_item");
        Type type = new TypeToken<MenuItem>() {}.getType();
        item = new Gson().fromJson(json_item, type);

        itemTitle = (TextView) findViewById(R.id.title);
        itemDescription = (TextView) findViewById(R.id.desc);
        itemPrice = (TextView) findViewById(R.id.price);
        cartButton = (Button) findViewById(R.id.addCartButton);

        itemTitle.setText(item.getName());
        itemDescription.setText(item.getDescription());
        itemPrice.setText(String.format("£%s", item.getPrice()));


        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment.addToCart(item, getApplicationContext());
                Toast.makeText(Item.this, "Item Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void addToCart(MenuItem item, Context context) {

    }
}
