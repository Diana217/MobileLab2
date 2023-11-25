package com.example.mobilelab2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    DBManager dbManager;
    Button btnAddSmartphone, btnAllSmartphones, btnContacts, btnAboutMe;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);

        btnAddSmartphone = findViewById(R.id.btnAddSmartphone);
        btnAllSmartphones = findViewById(R.id.btnSmartphones);
        btnContacts = findViewById(R.id.btnContacts);
        btnAboutMe = findViewById(R.id.btnAboutMe);

        btnAddSmartphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddSmartphoneActivity.class);
                startActivity(intent);
            }
        });
        btnAllSmartphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllSmartphonesActivity.class);
                startActivity(intent);
            }
        });
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });
        btnAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
            }
        });
    }
}