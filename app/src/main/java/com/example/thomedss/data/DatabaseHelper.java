package com.example.thomedss.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String USERS_TABLE = "USERS_TABLE";
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_USER_PASS = "USER_PASS";
    public static final String COLUMN_USER_REMEMBER = "USER_REMEMBER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    //create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement
                = "CREATE TABLE " + USERS_TABLE +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USER_ID + " TEXT," +
                COLUMN_USER_PASS + " TEXT," +
                COLUMN_USER_REMEMBER + " BOOL)";

        db.execSQL(createTableStatement);

    }

    //update the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        onCreate(db);
    }
}
