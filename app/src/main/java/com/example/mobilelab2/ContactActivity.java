package com.example.mobilelab2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    Button all_contacts_btn, selected_contacts_btn;
    ListView lv_contactsList;
    ArrayAdapter<Contact> contactsArrayAdapter;

    private static final int REQUEST_CONTACTS_PERMISSION = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);

        all_contacts_btn = findViewById(R.id.all_contacts_btn);
        selected_contacts_btn = findViewById(R.id.selected_contacts_btn);
        lv_contactsList = findViewById(R.id.lv_contactsList);

        all_contacts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkContactsPermission()) {
                    loadContacts();
                } else {
                    requestContactsPermission();
                }
            }
        });

        selected_contacts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkContactsPermission()) {
                    loadSortedContacts();
                } else {
                    requestContactsPermission();
                }
            }
        });

        Button btnBackToMain = findViewById(R.id.btnContactsToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkContactsPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestContactsPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACTS_PERMISSION);
    }

    private void loadContacts() {
        List<Contact> returnList = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        String contactName;
        String contactNumber;

        while (cursor.moveToNext()) {
            contactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            contactNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

            String[] nameParts = contactName.split(" ");
            String lastName = nameParts.length > 1 ? nameParts[nameParts.length - 1] : "";

            Contact contact = new Contact(nameParts[0], lastName, contactNumber, "");
            returnList.add(contact);
        }
        cursor.close();

        contactsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, returnList);
        lv_contactsList.setAdapter(contactsArrayAdapter);
    }

    private void loadSortedContacts() {
        List<Contact> returnList = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        String contactName;
        String contactNumber;

        while (cursor.moveToNext()) {
            contactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            contactNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

            // Check if the last name is longer than 10 letters
            String[] nameParts = contactName.split(" ");
            String lastName = nameParts.length > 1 ? nameParts[nameParts.length - 1] : "";
            if (lastName.length() > 10) {
                Contact contact = new Contact(nameParts[0], lastName, contactNumber, "");
                returnList.add(contact);
            }
        }
        cursor.close();

        contactsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, returnList);
        lv_contactsList.setAdapter(contactsArrayAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CONTACTS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
            } else {
            }
        }
    }
}