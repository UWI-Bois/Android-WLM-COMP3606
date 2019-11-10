package com.example.tut.models;

import android.provider.BaseColumns;

public final class CartContract {
    private  static final String INT_TYPE = " INT";
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + CartEntry.TABLE_NAME + " ("+
            CartEntry._ID + INT_TYPE + " PRIMARY KEY, " +
            CartEntry.ITEM + INT_TYPE + " NOT NULL, " +
            CartEntry.TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ");";
    public static abstract class CartEntry implements BaseColumns{
        public static final String TABLE_NAME = "cart";
        public static final String ITEM = "item";
        public static final String TIME = "timecreated";
    }

    public static String DROP_TABLE(){
        String str = "DROP TABLE " + CartEntry.TABLE_NAME + ";";
        return str;
    }
}
