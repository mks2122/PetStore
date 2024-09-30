package com.example.petshopapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import org.tensorflow.lite.Interpreter;
//import org.tensorflow.lite.guide.model.Model;



import androidx.appcompat.app.AppCompatActivity;







public class MainActivity extends AppCompatActivity {

//    Interpreter interpreter = new Interpreter(Model.load("yolov3.tflite"));


    EditText username, password;
    Button button_login;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.editTextTextUsername);
        password = (EditText)findViewById(R.id.editTextTextPassword);

        button_login = findViewById(R.id.buttonLogin);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Login Button Clicked");

                signIn(username.getText().toString(), password.getText().toString());
            }
        });
    }

    private void signIn(String username, String password) {
        // For now, just log the username and password and navigate to the shop activity
        Log.d(TAG, "Username: " + username + ", Password: " + password);

        Intent userHome = new Intent(MainActivity.this, shop.class);
        startActivity(userHome);
    }
}
