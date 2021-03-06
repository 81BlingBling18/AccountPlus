package com.happycoding.uniquehust.accountplus.database;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.global.Lg;
import com.happycoding.uniquehust.accountplus.items.AccountItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yifan on 11/3/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper databaseHelper = new DatabaseHelper(AccountPlusApp.getInstance().getApplicationContext());
    private static SQLiteDatabase database = databaseHelper.getWritableDatabase();
    private static final String TEXT_TYPE = " TEXT";
    public static final String REAL_TYPE = " REAL";
    public static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AccountDatabaseContract.AccountEntry.TABLE_NAME + " (" +
                    AccountDatabaseContract.AccountEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_TYPE + INTEGER_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_AMOUNT + REAL_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_YEAR + INTEGER_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_MONTH + INTEGER_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_DAY + INTEGER_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_PIC_TIMESTAMP + INTEGER_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_ICON_ID + INTEGER_TYPE +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AccountDatabaseContract.AccountEntry.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Account.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public static void add(AccountItem item) {
        ContentValues values = new ContentValues();
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_TYPE, item.getType());
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_TITLE, item.getTitle());
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_AMOUNT, item.getAmount());
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_DESCRIPTION, item.getDescription());
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_YEAR, item.getYear());
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_MONTH, item.getMonth());
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_DAY, item.getDay());
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_ICON_ID, item.getIconID());
        values.put(AccountDatabaseContract.AccountEntry.COLUMN_NAME_PIC_TIMESTAMP,item.getPicTimeStamp());
        database.insert(AccountDatabaseContract.AccountEntry.TABLE_NAME, null, values);
    }

    public static void update(AccountItem item) {
        //TODO: What the fuck;
    }

    public static ArrayList<AccountItem> getAll() {
        ArrayList<AccountItem> list = new ArrayList<>();
        Cursor cursor = database.query(AccountDatabaseContract.AccountEntry.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                AccountItem item = new AccountItem(

                        cursor.getInt(1)
                        , cursor.getString(2)
                        , cursor.getDouble(3)
                        , cursor.getString(4)
                        , cursor.getInt(5)
                        , cursor.getInt(6)
                        , cursor.getInt(7)
                        , cursor.getInt(8)
                        , cursor.getInt(9));
                list.add(item);

            } while (cursor.moveToNext());
        }
        return list;
    }

    public static double getMonthIncome(int year, int month) {
        String[] args = {"" + year, "" + month};
        ArrayList<AccountItem> list = new ArrayList<AccountItem>();
        Cursor cursor = database.rawQuery("SELECT * FROM account WHERE year = ? AND month = ? AND type = 1", args);
        if (cursor.moveToFirst()) {
            do {
                AccountItem item = new AccountItem(
                        cursor.getInt(1)
                        , cursor.getString(2)
                        , cursor.getDouble(3)
                        , cursor.getString(4)
                        , cursor.getInt(5)
                        , cursor.getInt(6)
                        , cursor.getInt(7)
                        , cursor.getInt(8)
                        , cursor.getInt(9));
                list.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        double sum = 0;
        for (AccountItem i : list) {
            sum += i.getAmount();
        }
        return sum;
    }

    public static double getMonthOutcome(int year, int month) {
        String[] args = {"" + year, "" + month};
        ArrayList<AccountItem> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM account WHERE year = ? AND month = ? AND type = 0", args);
        if (cursor.moveToFirst()) {
            do {
                AccountItem item = new AccountItem(
                        cursor.getInt(1)
                        , cursor.getString(2)
                        , cursor.getDouble(3)
                        , cursor.getString(4)
                        , cursor.getInt(5)
                        , cursor.getInt(6)
                        , cursor.getInt(7)
                        , cursor.getInt(8)
                        , cursor.getInt(9));
                list.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        double sum = 0;
        for (AccountItem i : list) {
            sum += i.getAmount();
        }
        return sum;
    }

    public static  ArrayList<AccountItem> getMonthDetail(int year, int month) {
        ArrayList<AccountItem> list = new ArrayList<>();
        String[] args = {"" + year, "" + month};
        Cursor cursor = database.rawQuery("SELECT * FROM account WHERE year = ? AND month = ?", args);
        if (cursor.moveToFirst()) {
            do {
                AccountItem item = new AccountItem(
                        cursor.getInt(1)
                        , cursor.getString(2)
                        , cursor.getDouble(3)
                        , cursor.getString(4)
                        , cursor.getInt(5)
                        , cursor.getInt(6)
                        , cursor.getInt(7)
                        , cursor.getInt(8)
                        , cursor.getInt(9));
                list.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Collections.sort(list);

        return list;
    }

}