package com.happycoding.uniquehust.accountplus;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.happycoding.uniquehust.accountplus.DataBase.AccountDatabaseContract;
import com.happycoding.uniquehust.accountplus.DataBase.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Test insert data into database;
/*        new Thread() {
            @Override
            public void run() {
                super.run();
                db = mDbHelper.getWritableDatabase();
            }
        }.start();

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_TYPE, "test");
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_TITLE, "test");
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_AMOUNT, 34843765);
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_DESCRIPTION, "test");
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_TIMESTAMP, "TEST");
        db.insert(AccountDatabaseContract.AccountEntry.TABLE_NAME, null, values);*/
    }
}
