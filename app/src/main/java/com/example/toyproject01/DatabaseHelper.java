package com.example.toyproject01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "ToyProject01.db";
    private static final String SQL_CREATE_ENTRIES =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                    Database.DatabaseEntity.TABLE_NAME,
                    Database.DatabaseEntity._ID,
                    Database.DatabaseEntity.COLUMN_NAME_CUSTID,
                    Database.DatabaseEntity.COLUMN_NAME_LANGCD,
                    Database.DatabaseEntity.COLUMN_NAME_CITYCD,
                    Database.DatabaseEntity.COLUMN_NAME_VISITYN);
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Database.DatabaseEntity.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
