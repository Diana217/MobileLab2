package com.example.mobilelab2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter {
    public CustomCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_smartphone, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewProducer = view.findViewById(R.id.textViewProducer);
        TextView textViewModel = view.findViewById(R.id.textViewModel);
        TextView textViewScreenDiagonal = view.findViewById(R.id.textViewScreenDiagonal);
        TextView textViewAddress = view.findViewById(R.id.textViewAddress);
        TextView textViewLongitude = view.findViewById(R.id.textViewLongitude);
        TextView textViewLatitude = view.findViewById(R.id.textViewLatitude);
        TextView textViewPrice = view.findViewById(R.id.textViewPrice);

        // Get data from the cursor
        String producer = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_PRODUCER));
        String model = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_MODEL));
        String screen_diagonal = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_SCREEN_DIAGONAL));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_ADDRESS));
        String longitude = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_ADDRESS_LONGITUDE));
        String latitude = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_ADDRESS_LATITUDE));
        String price = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.SMARTPHONE_PRICE));

        // Set data to the UI elements
        textViewProducer.setText(producer);
        textViewModel.setText(model);
        textViewScreenDiagonal.setText(screen_diagonal);
        textViewAddress.setText(address);
        textViewLongitude.setText(longitude);
        textViewLatitude.setText(latitude);
        textViewPrice.setText(price);
    }
}

