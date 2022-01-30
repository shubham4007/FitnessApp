package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class register extends AppCompatActivity {
    EditText email,password,age,height,weight;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.inputUsername);
        password = findViewById(R.id.inputPassword);
        age = findViewById(R.id.ageinput);
        height = findViewById(R.id.heightinput);
        weight = findViewById(R.id.weightinput);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_text = email.getText().toString() ;
                String password_text = password.getText().toString() ;
                String age_text = age.getText().toString() ;
                String height_text = height.getText().toString() ;
                String weight_text = weight.getText().toString() ;

                DbHandler dbHandler = new DbHandler(register.this);
                dbHandler.insertUserDetails(email_text,password_text,age_text,height_text,weight_text);

                ArrayList<HashMap<String, String>> userList = new ArrayList<>();
                userList = dbHandler.GetUsers();
                System.out.println(userList);


                Toast.makeText(register.this, "User Registeration Successfull", Toast.LENGTH_LONG).show();
                startActivity(new Intent(register.this,Dashboard.class));
            }
        });

    }
}