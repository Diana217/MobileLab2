package com.example.mobilelab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SmartphoneDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_smartphone);

        // Assuming you have TextViews in the layout to display smartphone details
        TextView producerTextView = findViewById(R.id.textViewProducer);
        TextView modelTextView = findViewById(R.id.textViewModel);
        TextView screenDiagonalTextView = findViewById(R.id.textViewScreenDiagonal);
        TextView addressTextView = findViewById(R.id.textViewAddress);
        TextView longitudeTextView = findViewById(R.id.textViewLongitude);
        TextView latitudeTextView = findViewById(R.id.textViewLatitude);
        TextView priceTextView = findViewById(R.id.textViewPrice);

        // Retrieve data from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String producer = intent.getStringExtra("producer");
            String model = intent.getStringExtra("model");
            double screen_diagonal = intent.getDoubleExtra("screen_diagonal", 0.0);
            String address = intent.getStringExtra("address");
            double longitude = intent.getDoubleExtra("longitude", 0.0);
            double latitude =  intent.getDoubleExtra("latitude", 0.0);
            double price =  intent.getDoubleExtra("price", 0.0);

            // Set the data to TextViews
            producerTextView.setText("Producer: " + producer);
            modelTextView.setText("Model: " + model);
            screenDiagonalTextView.setText("Screen Diagonal: " + screen_diagonal);
            addressTextView.setText("Address: " + address);
            longitudeTextView.setText("Longitude: " + longitude);
            latitudeTextView.setText("Latitude: " + latitude);
            priceTextView.setText("Price: " + price);
        }
    }
}

