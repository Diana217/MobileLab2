package com.example.mobilelab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DBManager {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context _context) {
        context = _context;
    }
    public DBManager open() throws SQLDataException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    public void insert_smartphone(String producer, String model, Double screen_diagonal, String address, Double longitude, Double latitude, Double price) throws SQLDataException {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.SMARTPHONE_PRODUCER, producer);
        contentValues.put(DBHelper.SMARTPHONE_MODEL, model);
        contentValues.put(DBHelper.SMARTPHONE_SCREEN_DIAGONAL, screen_diagonal);
        contentValues.put(DBHelper.SMARTPHONE_ADDRESS, address);
        contentValues.put(DBHelper.SMARTPHONE_ADDRESS_LONGITUDE, longitude);
        contentValues.put(DBHelper.SMARTPHONE_ADDRESS_LATITUDE, latitude);
        contentValues.put(DBHelper.SMARTPHONE_PRICE, price);
        database.insert(DBHelper.TABLE_SMARTPHONES, null, contentValues);
    }
    public Cursor fetch_smartphone() {
        try {
            open();
            String[] columns = new String[] {
                    DBHelper.SMARTPHONE_ID + " as _id",
                    DBHelper.SMARTPHONE_PRODUCER,
                    DBHelper.SMARTPHONE_MODEL,
                    DBHelper.SMARTPHONE_SCREEN_DIAGONAL,
                    DBHelper.SMARTPHONE_ADDRESS,
                    DBHelper.SMARTPHONE_ADDRESS_LONGITUDE,
                    DBHelper.SMARTPHONE_ADDRESS_LATITUDE,
                    DBHelper.SMARTPHONE_PRICE
            };
            Cursor cursor = database.query(
                    DBHelper.TABLE_SMARTPHONES,
                    columns,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            if (cursor != null) {
                cursor.moveToFirst();
            }
            return cursor;
        } catch (SQLDataException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Cursor getMotorolaModelsAbove5Inches() throws SQLDataException {
        try {
            open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }

        String selection = DBHelper.SMARTPHONE_MODEL + " = ? AND " +
                DBHelper.SMARTPHONE_SCREEN_DIAGONAL + " > ?";

        String[] selectionArgs = new String[]{"Motorola", "5.0"};

        String[] columns = new String[]{DBHelper.SMARTPHONE_ID + " as _id", DBHelper.SMARTPHONE_PRODUCER,
                DBHelper.SMARTPHONE_MODEL, DBHelper.SMARTPHONE_SCREEN_DIAGONAL,
                DBHelper.SMARTPHONE_ADDRESS, DBHelper.SMARTPHONE_ADDRESS_LONGITUDE,
                DBHelper.SMARTPHONE_ADDRESS_LATITUDE, DBHelper.SMARTPHONE_PRICE};

        Cursor cursor = database.query(DBHelper.TABLE_SMARTPHONES, columns, selection, selectionArgs,
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public double calculateAverageDiagonal() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT AVG(" + DBHelper.SMARTPHONE_SCREEN_DIAGONAL + ") FROM " + DBHelper.TABLE_SMARTPHONES;

        Cursor cursor = db.rawQuery(query, null);
        double averageDiagonal = 0;

        if (cursor != null && cursor.moveToFirst()) {
            averageDiagonal = cursor.getDouble(0);
            cursor.close();
        }

        return averageDiagonal;
    }

    public void deleteSmartphones() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // Use the DELETE statement with a WHERE clause to delete the first 20 records
            String deleteQuery = "DELETE FROM " + DBHelper.TABLE_SMARTPHONES +
                    " WHERE " + DBHelper.SMARTPHONE_ID + " IN " +
                    "(SELECT " + DBHelper.SMARTPHONE_ID +
                    " FROM " + DBHelper.TABLE_SMARTPHONES +
                    " LIMIT 2)";

            db.execSQL(deleteQuery);
        } finally {
            db.close();
        }
    }

}
