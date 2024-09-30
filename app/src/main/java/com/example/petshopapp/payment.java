package com.example.petshopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class payment extends AppCompatActivity {

    private int order_number;

    final int min = 1000;
    final int max = 9999;

    Random rand;
    Button payment_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payment_button = findViewById(R.id.buttonPay);
        payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                order_number = rand.nextInt((max - min) + 1) + min;

                Toast.makeText(payment.this, "Order Number " + order_number, Toast.LENGTH_LONG).show();

                basket_dbHandler dbHandler = new basket_dbHandler(payment.this);
                dbHandler.ResetDatabase();

                Intent backToShop = new Intent(payment.this, shop.class);
                startActivity(backToShop);
            }
        });

    }
}