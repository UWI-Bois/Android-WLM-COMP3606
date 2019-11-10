package com.example.tut.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "UWIPeersDB";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // put all of the sql create operations here
        db.execSQL(CartContract.DROP_TABLE());
        db.execSQL(ItemContract.DROP_TABLE());
        db.execSQL(CartContract.CREATE_TABLE);
        db.execSQL(ItemContract.CREATE_TABLE);
        db.execSQL(ItemContract.CREATE_ITEMS());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // if we upgrade the databse we can put the code to change the table
        // and or thefields as is needed
        if(oldVersion < 2){
            db.execSQL(ItemContract.CREATE_TABLE);
            db.execSQL(ItemContract.CREATE_ITEMS());
        }
    }

}
