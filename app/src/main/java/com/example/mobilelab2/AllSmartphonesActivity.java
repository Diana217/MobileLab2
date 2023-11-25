package com.example.mobilelab2;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLDataException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLDataException;

public class AllSmartphonesActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private CustomCursorAdapter adapter;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_smartphones);

        dbManager = new DBManager(this);
        listView = findViewById(R.id.list_smartphones);

        Button btnBackToMain = findViewById(R.id.btnToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllSmartphonesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnShowSample = findViewById(R.id.btnSmartphoneSampling);
        btnShowSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dbManager.open();
                    Cursor cursor = dbManager.getMotorolaModelsAbove5Inches();

                    if (cursor != null && cursor.moveToFirst()) {
                        adapter = new CustomCursorAdapter(AllSmartphonesActivity.this, cursor);
                        listView.setAdapter(adapter);
                    } else {
                        Toast.makeText(AllSmartphonesActivity.this, "No Motorola models above 5 inches", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLDataException e) {
                    throw new RuntimeException(e);
                } finally {
                    dbManager.close();
                }
            }
        });

        Button btnAverage = findViewById(R.id.btnAvgDiagonal);
        btnAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dbManager.open();
                    double averageDiagonal = dbManager.calculateAverageDiagonal();
                    Toast.makeText(AllSmartphonesActivity.this, "Average Diagonal: " + averageDiagonal, Toast.LENGTH_SHORT).show();
                } catch (SQLDataException e) {
                    throw new RuntimeException(e);
                } finally {
                    dbManager.close();
                }
            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the cursor at the clicked position
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);

                // Extract information from the cursor
                String producer = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_PRODUCER));
                String model = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_MODEL));
                double screen_diagonal = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_SCREEN_DIAGONAL)));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_ADDRESS));
                double longitude = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_ADDRESS_LONGITUDE)));
                double latitude = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_ADDRESS_LATITUDE)));
                double price = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_PRICE)));

                // Create an Intent to start a new activity to display the details
                Intent intent = new Intent(AllSmartphonesActivity.this, SmartphoneDetailsActivity.class);
                intent.putExtra("producer", producer);
                intent.putExtra("model", model);
                intent.putExtra("screen_diagonal", screen_diagonal);
                intent.putExtra("address", address);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                intent.putExtra("price", price);

                startActivity(intent);
            }
        });*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
                String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_ADDRESS));

                Intent intent = new Intent(AllSmartphonesActivity.this, GeolocationActivity.class);
                intent.putExtra("CITY_NAME", address);
                startActivity(intent);
            }
        });

        try {
            dbManager.open();
            Cursor cursor = dbManager.fetch_smartphone();

            if (cursor != null && cursor.moveToFirst()) {
                adapter = new CustomCursorAdapter(this, cursor);
                listView = findViewById(R.id.list_smartphones);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(this, "No data in the database", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        } finally {
            dbManager.close();
        }
    }
}
