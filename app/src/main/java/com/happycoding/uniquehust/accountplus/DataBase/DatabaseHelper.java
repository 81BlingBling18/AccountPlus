package com.happycoding.uniquehust.accountplus.DataBase;

import android.content.Context;
import android.database.AbstractCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.happycoding.uniquehust.accountplus.DataBase.*;

/**
 * Created by yifan on 11/3/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    public static final String  REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AccountDatabaseContract.AccountEntry.TABLE_NAME + " (" +
                    AccountDatabaseContract.AccountEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_AMOUNT + REAL_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    AccountDatabaseContract.AccountEntry.COLUMN_NAME_TIMESTAMP + TEXT_TYPE + COMMA_SEP +
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
}
