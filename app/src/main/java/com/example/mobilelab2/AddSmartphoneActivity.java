package com.example.mobilelab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLDataException;

public class AddSmartphoneActivity extends AppCompatActivity {
    DBManager dbManager;
    private EditText producerEditText;
    private EditText modelEditText;
    private EditText screenDiagonalEditText;
    private EditText addressEditText;
    private EditText addressLongitudeEditText;
    private EditText addressLatitudeEditText;
    private EditText priceEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_smartphone);

        dbManager = new DBManager(this);

        producerEditText = findViewById(R.id.edit_producer);
        modelEditText = findViewById(R.id.edit_model);
        screenDiagonalEditText = findViewById(R.id.edit_screen_diagonal);
        addressEditText = findViewById(R.id.edit_address);
        addressLongitudeEditText = findViewById(R.id.edit_longitude);
        addressLatitudeEditText = findViewById(R.id.edit_latitude);
        priceEditText = findViewById(R.id.edit_price);

        Button btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSmartphoneActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnAddSmartphone = findViewById(R.id.btn_add_smartphone);
        btnAddSmartphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addSmartphone();
                    Intent intent = new Intent(AddSmartphoneActivity.this, AllSmartphonesActivity.class);
                    startActivity(intent);
                } catch (SQLDataException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void addSmartphone() throws SQLDataException {
        String producer = producerEditText.getText().toString();
        String model = modelEditText.getText().toString();
        double screen_diagonal = Double.parseDouble(screenDiagonalEditText.getText().toString());
        String address = addressEditText.getText().toString();
        double address_longitude = Double.parseDouble(addressLongitudeEditText.getText().toString());
        double address_latitude = Double.parseDouble(addressLatitudeEditText.getText().toString());
        double price = Double.parseDouble(priceEditText.getText().toString());

        dbManager.insert_smartphone(producer, model, screen_diagonal, address, address_longitude, address_latitude, price);
    }
}
