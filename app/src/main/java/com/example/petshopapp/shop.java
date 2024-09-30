package com.example.petshopapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class shop extends AppCompatActivity implements cardViewRecyclerViewAdapter.ItemClickListener {

    private int itemPosition, counter = 0;

    cardViewRecyclerViewAdapter adapter;
    Button checkout_button;
    TextView basket_count;

    ArrayList<Integer> itemImages = new ArrayList<Integer>();
    ArrayList<Integer> itemPrices = new ArrayList<>();
    ArrayList<String> itemNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        itemNames.add("Dog Food");
        itemNames.add("Fish Food");
        itemNames.add("Rabbit Food");
        itemNames.add("Mice Food");
        itemNames.add("Duck Food");
        itemNames.add("Cat Food");
        itemNames.add("Chicken Food");
        itemNames.add("Goose Food");
        itemNames.add("Alpaca Food");
        itemNames.add("Bird Food");

        itemPrices.add(12);
        itemPrices.add(14);
        itemPrices.add(25);
        itemPrices.add(23);
        itemPrices.add(24);
        itemPrices.add(26);
        itemPrices.add(19);
        itemPrices.add(18);
        itemPrices.add(32);
        itemPrices.add(11);

        itemImages.add(R.drawable.dog_food);
        itemImages.add(R.drawable.fish_food);
        itemImages.add(R.drawable.rabbit_food);
        itemImages.add(R.drawable.mice_food);
        itemImages.add(R.drawable.duck_food);
        itemImages.add(R.drawable.cat_food);
        itemImages.add(R.drawable.chick_food);
        itemImages.add(R.drawable.goose_food);
        itemImages.add(R.drawable.alpacas_food);
        itemImages.add(R.drawable.bird_food);

        RecyclerView recyclerView = findViewById(R.id.rvStore);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));

        adapter = new cardViewRecyclerViewAdapter(this, itemNames, itemPrices, itemImages);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        basket_count = (TextView)findViewById(R.id.itemCount);

        checkout_button = findViewById(R.id.rvCheckoutButton);
        checkout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent checkoutPage = new Intent(shop.this, basket.class);
                startActivity(checkoutPage);
            }
        });

    }

    public void onItemClick (View view, int position) {
        itemPosition = position;
        PopupMenu popup = new PopupMenu(shop.this, view);
        popup.setOnMenuItemClickListener(shop.this::onMenuItemClick);
        popup.inflate(R.menu.shop_popup_menu);
        popup.show();
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.omAddToBasket:
                addItemToBasket();
                counter++;
                basket_count.setVisibility(View.VISIBLE);
                basket_count.setText("Basket: " + Integer.toString(counter));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void addItemToBasket() {
        String itemName = itemNames.get(itemPosition);
        int price = itemPrices.get(itemPosition);
        basket_dbHandler dbHandler = new basket_dbHandler(shop.this);
        dbHandler.insertBasketItem(itemName, price);

        Toast.makeText(this, itemName + " Has been added to Basket", Toast.LENGTH_SHORT).show();
    }
}