package com.example.petshopapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class basket extends AppCompatActivity {

    TextView totalPrice;
    Button purchaseButton;

    private int sumPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        basket_dbHandler db = new basket_dbHandler(this);
        ArrayList<HashMap<String, String>> itemList = db.GetItems();

        ListView lv = (ListView) findViewById(R.id.item_list);
        ListAdapter adapter = new SimpleAdapter(basket.this, itemList, R.layout.list_row,
                new String[]{"name", "price"},
                new int[]{R.id.lvItemName, R.id.lvItemPrice});

        lv.setAdapter(adapter);

        ArrayList<Integer> priceList = db.getPrices();

        for (int number : priceList) {
            sumPrice += number;
        }

        totalPrice = (TextView) findViewById(R.id.tvTotalPrice);
        totalPrice.setText("$" + Integer.toString(sumPrice));

        purchaseButton = findViewById(R.id.purchase_button);
        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentPage = new Intent(basket.this, payment.class);
                startActivity(paymentPage);
            }
        });

    }
}