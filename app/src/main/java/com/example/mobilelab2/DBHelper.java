package com.example.mobilelab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "MobileDB";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_SMARTPHONES = "SMARTPHONES";
    static final String SMARTPHONE_ID = "_ID";
    static final String SMARTPHONE_PRODUCER = "producer";
    static final String SMARTPHONE_MODEL = "model";
    static final String SMARTPHONE_SCREEN_DIAGONAL = "screen_diagonal";
    static final String SMARTPHONE_ADDRESS = "address";
    static final String SMARTPHONE_ADDRESS_LONGITUDE = "address_longitude";
    static final String SMARTPHONE_ADDRESS_LATITUDE = "address_latitude";
    static final String SMARTPHONE_PRICE = "price";

    static final String TABLE_CONTACTS = "CONTACTS";
    static final String CONTACT_ID = "_ID";
    static final String CONTACT_FIRST_NAME = "first_name";
    static final String CONTACT_SECOND_NAME = "second_name";
    static final String CONTACT_PHONE_NUMBER = "phoneNumber";

    private static final String CREATE_SMARTPHONES = "CREATE TABLE " + TABLE_SMARTPHONES + " ( "
            + SMARTPHONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SMARTPHONE_PRODUCER + " TEXT, "
            + SMARTPHONE_MODEL + " TEXT, "
            + SMARTPHONE_SCREEN_DIAGONAL + " REAL, "
            + SMARTPHONE_ADDRESS + " TEXT, "
            + SMARTPHONE_ADDRESS_LONGITUDE + " REAL, "
            + SMARTPHONE_ADDRESS_LATITUDE + " REAL, "
            + SMARTPHONE_PRICE + " REAL)";

    private static final String CREATE_CONTACTS = "CREATE TABLE " + TABLE_CONTACTS + " ( "
            + CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CONTACT_FIRST_NAME + " TEXT, "
            + CONTACT_SECOND_NAME + " TEXT, "
            + CONTACT_PHONE_NUMBER + " TEXT)";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SMARTPHONES);
        sqLiteDatabase.execSQL(CREATE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SMARTPHONES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
}
