package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView register_Activity;
    EditText email,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DbHandler dbHandler = new DbHandler(MainActivity.this);
        register_Activity = findViewById(R.id.register);
        login = findViewById(R.id.login);
        email = findViewById(R.id.inputUsername);
        password = findViewById(R.id.inputPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_text = email.getText().toString();

                if(dbHandler.GetUserByUserId(email_text)){
                    startActivity(new Intent(MainActivity.this,Dashboard.class));
                }else
                    Toast.makeText(MainActivity.this, "User not registered", Toast.LENGTH_LONG).show();
            }
        });

        register_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,register.class));
            }
        });

    }
}